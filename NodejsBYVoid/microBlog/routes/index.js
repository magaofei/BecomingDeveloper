var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

//Hello
router.hello = function(req, res) {
  res.send('The time is' + new Date().toString());
};
// exports.get('/', function(req, res) {
//   res.render('hello');
// });

module.exports = router;
