var contactManagerApp = 'contactManagerApp';

angular.module(contactManagerApp, [
    contactManagerApp + '.directives',
    contactManagerApp + '.controllers',
    contactManagerApp + '.services',
    contactManagerApp + '.factories',
    contactManagerApp + '.filters',
    contactManagerApp + '.routes',
    'ui.bootstrap',
    'ngRoute'
]);
angular.module(contactManagerApp + '.directives', []);
angular.module(contactManagerApp + '.controllers', []);
angular.module(contactManagerApp + '.services', []);
angular.module(contactManagerApp + '.factories', []);
angular.module(contactManagerApp + '.filters', []);
angular.module(contactManagerApp + '.routes', []);

