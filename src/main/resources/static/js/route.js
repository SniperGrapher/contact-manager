angular.module(contactManagerApp + '.routes', ['ngRoute'])

// configure routes
    .config(function ($routeProvider) {
        $routeProvider

        // route for the home page
            .when('/', {
                templateUrl: 'pages/users/list.html',
                controller: 'mainController'
            })
            .when('/users/:id', {
                templateUrl: 'pages/users/show.html',
                controller: 'viewUserController'
            })
            .when('/edit/:id', {
                templateUrl: 'pages/users/edit.html',
                controller: 'editUserController'
            })
            .when('/add', {
                templateUrl: 'pages/users/edit.html',
                controller: 'editUserController'
            })
            .otherwise({
                redirectTo: '/'
            });
    })