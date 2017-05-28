'use strict';
var Generator = require('yeoman-generator');
var chalk = require('chalk');
var yosay = require('yosay');

// Use the main (app) to install the whole application.
// example use gen-java:service to install a compoment.

module.exports = Generator.extend({
  prompting: function () {
    // Have Yeoman greet the user.
    this.log(yosay(
      'Spring MVC Service generator!'
    ));

// request service class name.
// request if Interface is needed.
// request if Dto is needed, yes import dto extends BaseDto.
//
    var prompts = [{
      type: 'input',
      name: 'serviceClass',
      message: 'Insert service name.'
    },
      {
        type: 'confirm',
        name: 'useInterface',
        message: 'Use an interface for the Serive class?',
      },
      {
        when: function(response) {
          return response.useInterface;
        },
        name: 'useDto',
        message: 'Use a DTO'
      }
    ];

    return this.prompt(prompts).then(function (props) {
      var parts = props.serviceClass.split('.')
      var name  = parts.pop(); // remove the last item of the array.

      // To access props later use this.props.someAnswer;
      this.props = props;
      this.props.serviceClass = name;
      this.props.packageName = parts.join('.');
      this.props.fileDir = parts.join('/')
    }.bind(this));
  },

  
  writing: function () {
    // Copy the Base Interface.
    // Copy the Interface.
    this.fs.copyTpl(
      this.templatePath('_Service.java'),
      this.destinationPath('public/' + this.props.serviceClass + 'Service.java'),
      {
          packageName: this.props.packageName,
          serviceClass: this.props.serviceClass
      }
    );
    // Copy the Class.
    this.fs.copyTpl(
      this.templatePath('_ServiceImpl.java'),
      this.destinationPath(this.props.fileDir + '/' + this.props.serviceClass + 'ServiceImpl.java'),
      {
          packageName: this.props.packageName,
          serviceClass: this.props.serviceClass
      }
    );
  },

  install: function () {
    this.installDependencies();
  }
});
