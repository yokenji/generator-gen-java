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
      'Spring MVC Repository class generator!'
    ));

// request service class name.
// request if Interface is needed.
// request if Dto is needed, yes import dto extends BaseDto.
//
    var prompts = [
      {
        type: 'input',
        name: 'repositoryClass',
        message: 'Insert repository class name.'
      },
      {
        type: 'confirm',
        name: 'useInterface',
        message: 'Use an Interface for this class?',
        default: true
      },
      {
        when: function(response){
          return response.useInterface;
        },
        type: 'confirm',
        name: 'useBaseRepository',
        message: 'Extends from BaseRepository class?',
        default: true
      }
    ];

    return this.prompt(prompts).then(function (props) {
      var parts = props.repositoryClass.split('.')
      var name  = parts.pop(); // remove the last item of the array.

      // To access props later use this.props.someAnswer;
      this.props = props;
      this.props.repositoryClass = name;
      this.props.packageName = parts.join('.');
      this.props.fileDir = parts.join('/');
    }.bind(this));
  },

  
  writing: function () {
    if (this.props.useInterface) {
      // Copy the BaseRepository class if necessary.
      if (this.props.useBaseRepository) {
        this.fs.copyTpl(
          this.templatePath('_BaseRepository.java'),
          this.destinationPath('public/BaseRepository.java'),
          {
            packageName: this.props.packageName
          }
        )
      }

      // Copy the Repository interface if nessecarry.
      this.fs.copyTpl(
        this.templatePath('_Repository.java'),
        this.destinationPath('public/' +  this.props.repositoryClass + 'Repository.java'),
        {
          packageName: this.props.packageName,
          extendBaseRepository: this.props.useBaseRepository,
          repositoryClass: this.props.repositoryClass,
          entityClass: this.props.repositoryClass
        }
      );

    }

    // Copy the RepositoryJpa if no interface used the we call it Repository.
    this.fs.copyTpl(
      this.templatePath('_RepositoryJpa.java'),
      this.destinationPath('public/' + this.props.repositoryClass + 'Repository' + (this.props.useInterface == true ? 'Jpa' : '') + '.java'),
      {
        packageName: this.props.packageName,
        implementsBaseRepository: this.props.useInterface,
        repositoryClass: this.props.repositoryClass,
        repositoryClassPrefix: this.props.repositoryClass.substring(0, 1).toLowerCase()
      }
    )
    
  },

  install: function () {
    this.installDependencies();
  }
});
