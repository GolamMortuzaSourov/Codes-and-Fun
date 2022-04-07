//This is a program that I made while practicing extracting json data with python
import json
data = '''
{
  "name" : "Chuck",
  "phone" : {
  "type" : "int",
  "number" : "+1 656543645435"
  },
  "email" : {
    "hide" : "yes"
  }
}'''

info = json.loads(data)
print('Name : ', info["name"])
print('hide : ', info["email"]["hide"])
print('Phone : ', info["phone"]["number"])
