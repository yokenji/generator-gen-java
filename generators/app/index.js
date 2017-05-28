'use strict';
var Generator = require('yeoman-generator');
var chalk = require('chalk');
var yosay = require('yosay');

module.exports = Generator.extend({
  prompting: function () {
    // Have Yeoman greet the user.
    this.log(yosay(
      'Spring MVC Service generator!'
    ));

    function firstToLowerCase(text) {
      return text.substring(0,1).toLowerCase() + text.substring(1);
    }

    var prompts = [
      {
        type: 'input',
        name: 'serviceClass',
        message: 'Insert service name.'
      },
      {
        type: 'confirm',
        name: 'useInterface',
        message: 'Use an interface for this Service?',
        default: true
      },
      {
        when: function(response){
          return response.useInterface;
        },
        type: 'confirm',
        name: 'useBaseService',
        message: 'Extends from BaseService class?',
        default: true
      }
    ];

// The DTO/Repository will be quested according the Service name.
    return this.prompt(prompts).then(function (props) {
      var parts = props.serviceClass.split('.')
      var name  = parts.pop(); // remove the last item of the array.

      // To access props later use this.props.someAnswer;
      this.props = props;
      this.props.serviceClass = name + 'Service';
      this.props.packageName = parts.join('.');

      //_cc means camelCase.
      this.props.extendBaseService = this.props.useBaseService;
      this.props.serviceClass_cc = firstToLowerCase(this.props.serviceClass);
      this.props.dtoClass = name + 'Dto';
      this.props.dtoClass_cc = firstToLowerCase(this.props.dtoClass);
      this.props.entityClass = name;
      this.props.entityClass_cc = firstToLowerCase(this.props.entityClass);
      this.props.repositoryClass = name + 'Repository';
      this.props.repositoryClass_cc = firstToLowerCase(this.props.repositoryClass);

    }.bind(this));
  },

  
  writing: function () {
    if (this.props.useInterface) {
      // Copy the BaseService class if necessary.
      if (this.props.useBaseService) {
        this.fs.copyTpl(
          this.templatePath('_BaseService.java'),
          this.destinationPath('public/BaseService.java'),
          {
            packageName: this.props.packageName
          }
        )
      }

      // Copy the Service interface if necessary.
      this.fs.copyTpl(
        this.templatePath('_Service.java'),
        this.destinationPath('public/' + this.props.serviceClass + 'Service.java'),
        {
          packageName: this.props.packageName,
          extendBaseService: this.props.useBaseService,
          serviceClass: this.props.serviceClass,
          dtoClass: this.props.dtoClass,
        }
      )
    }

    // Copy the ServiceImpl, if no interface used then rename if to Service.
    this.fs.copyTpl(
      this.templatePath('_ServiceImpl.java'),
      this.destinationPath('public/' + this.props.serviceClass + 'Service' + (this.props.useInterface == true ? 'Impl' : '') + '.java'),
      {
        packageName: this.props.packageName,
        implementsBaseService: this.props.useInterface,
        serviceClass: this.props.serviceClass,
        dtoClass: this.props.dtoClass,
        entityClass: this.props.serviceClass,
        repositoryClass: this.props.repositoryClass,
        repositoryClass_cc: this.props.repositoryClass_cc
      }
    )

  },

  install: function () {
    this.installDependencies();
  }
});
