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
      'SpringMvc Entity generator!'
    ));

// request service class name.
// request if Interface is needed.
// request if Dto is needed, yes import dto extends BaseDto.
//
    var prompts = [{
      type: 'input',
      name: 'entityClass',
      message: 'Insert entity name.'
    },
      {
        type: 'confirm',
        name: 'useBaseEntity',
        message: 'Extens from the BaseEntity class?',
        default: true
      }
    ];

    return this.prompt(prompts).then(function (props) {
      var parts = props.entityClass.split('.')
      var name  = parts.pop(); // remove the last item of the array.

      // To access props later use this.props.someAnswer;
      this.props = props;
      this.props.entityClass = name;
      this.props.entityTableName = name;
      this.props.packageName = parts.join('.');
      this.props.fileDir = parts.join('/');
    }.bind(this));
  },

  
  writing: function () {
    // Copy the BaseEntity if extending from it.
    if (this.props.useBaseEntity) {
      this.fs.copyTpl(
        this.templatePath('_BaseEntity.java'),
        this.destinationPath('public/BaseEntity.java'),
        {
          packageName: this.props.packageName
        }
      );
    }

    // Copy the Entity.
    this.fs.copyTpl(
      this.templatePath('_Entity.java'),
      this.destinationPath('public/' + this.props.entityClass + '.java'),
      {
        packageName: this.props.packageName,
        entityTableName: this.props.entityTableName,
        entityClass: this.props.entityClass,
        extendBaseEntity: this.props.useBaseEntity
      }
    )

    // Copy the Entity.
    // this.fs.copyTpl(
    //   this.templatePath('_Entity.java'),
    //   this.destinationPath('public/model/' + this.props)
    // )
    // // Copy the Class.
    // this.fs.copyTpl(
    //   this.templatePath('_ServiceImpl.java'),
    //   this.destinationPath(this.props.fileDir + '/' + this.props.serviceClass + 'ServiceImpl.java'),
    //   {
    //       packageName: this.props.packageName,
    //       serviceClass: this.props.serviceClass
    //   }
    // );
  },

  install: function () {
    this.installDependencies();
  }
});
