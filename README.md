# internship-server-application
template for server application deployed on heroku 


### API

```bash
curl -XPOST -H "Content-Type:application/json" -v https://floating-garden-54811.herokuapp.com/user/register -d '{
  "username" : "Test11",
  "password" : "Test11Test",
  "firstName" : "Tester",
  "lastName" : "Tester",
  "gender" : "female",
  "yearOfBirth" : 1967
}'
```