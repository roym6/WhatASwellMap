from bs4 import BeautifulSoup
from urllib2 import urlopen
from HTMLParser import HTMLParser
from WhatASwellMap import SwellInfo
import json

BLACKS_URL = "http://www.surfline.com/surf-report/blacks-southern-california_4245/";
HB_NORTH_URL = "http://www.surfline.com/surf-report/hb-pier-northside-southern-california_4223/";
GOLDENWEST_URL = "http://www.surfline.com/surf-report/goldenwest-southern-california_4870/";
PORTO_URL = "http://www.surfline.com/surf-report/el-porto-southern-california_4900/";

class Swell(SwellInfo):
	def __init__(self):
		self.list = {'Blacks': BLACKS_URL, 'HB North': HB_NORTH_URL, 'Goldenwest': GOLDENWEST_URL, 'Porto': PORTO_URL}
		for key in self.list:
			self.list[key] = getInfo(self.list[key])
	
	def getSwell(self):
		jsonarray = json.dumps(self.list)
		return jsonarray

def getInfo(url):
	soup = make_soup(url)
	wave_range = soup.find(id="observed-wave-range")
	conditions = soup.find(id="observed-spot-conditions")
	return {'Wave Range': str(wave_range.get_text()), 'Conditions': str(conditions.get_text()), }
	
def make_soup(url):
	html = urlopen(url).read()
	return BeautifulSoup(html, "html.parser")