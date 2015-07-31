#from lxml import html
from bs4 import BeautifulSoup
from urllib2 import urlopen

#import requests

BLACKS_URL = "http://www.surfline.com/surf-report/blacks-southern-california_4245/"
HB_NORTH_URL = "http://www.surfline.com/surf-report/hb-pier-northside-southern-california_4223/"
GOLDENWEST_URL = "http://www.surfline.com/surf-report/goldenwest-southern-california_4870/"
PORTO_URL = "http://www.surfline.com/surf-report/el-porto-southern-california_4900/"

def make_soup(url):
	html = urlopen(url).read()
	return BeautifulSoup(html, "lxml")

def get_swell(url):
	soup = make_soup(url)
	wave_range = soup.find(id="observed-wave-range")
	conditions = soup.find(id="observed-spot-conditions")
	return {'Wave Range': str(wave_range.get_text()), 'Conditions': str(conditions.get_text())}

info = {'Blacks': BLACKS_URL, 'HB North': HB_NORTH_URL, 'Goldenwest': GOLDENWEST_URL, 'Porto': PORTO_URL}
for key in info:
	info[key] = get_swell(info[key])
print info