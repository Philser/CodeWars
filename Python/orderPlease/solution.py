import re

def order(sentence):
  # code here
  words = sentence.split(" ")
  if(len(words) == 0):
      return '' 
      
  list = []
  map = {}
  number_rx = r'[0-9]'
  for word in words:
      print(word)
      result = re.search(number_rx, word)
      if(result):
          map[int(result.group(0))] = word
  
  for i in range(0, len(map)):
      #print(map[i + 1])
      list.append(map[i + 1])
  
  return ' '.join(list)
