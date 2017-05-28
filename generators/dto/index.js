'use strict';
var Generator = require('yeoman-generator');
var chalk = require('chalk');
var yosay = require('yosay');

module.exports = Generator.extend({
  prompting: function () {
    // Have Yeoman greet the user.
    this.log(yosay(
      'Spring MVC DTO class generator!'
    ));

    var prompts = [
      {
        type: 'input',
        name: 'dtoClass',
        message: 'Insert dto class name.'
      },
      {
        type: 'confirm',
        name: 'useBaseDto',
        message: 'Extens from the BaseDto class?',
        default: true
      }
    ];

    return this.prompt(prompts).then(function (props) {
      var parts = props.dtoClass.split('.')
      var name  = parts.pop(); // remove the last item of the array.

      // To access props later use this.props.someAnswer;
      this.props = props;
      this.props.dtoClass = name;
      this.props.packageName = parts.join('.');
      this.props.fileDir = parts.join('/');
    }.bind(this));
  },

  
  writing: function () {
    // Copy the BaseEntity if extending from it.
    if (this.props.useBaseDto) {
      this.fs.copyTpl(
        this.templatePath('_BaseDto.java'),
        this.destinationPath('public/BaseDto.java'),
        {
          packageName: this.props.packageName
        }
      );
    }

    // Copy the Entity.
    this.fs.copyTpl(
      this.templatePath('_Dto.java'),
      this.destinationPath('public/' + this.props.dtoClass + 'Dto.java'),
      {
        packageName: this.props.packageName,
        dtoClass: this.props.dtoClass,
        extendBaseDto: this.props.useBaseDto
      }
    );
    
  },

  install: function () {
    this.installDependencies();
  }
});
