'use strict';
angular.module(contactManagerApp + '.controllers')
    .controller('mainController', ['$scope', '$rootScope', '$location',
        'userService', 'Data', 'UserDetails', function ($scope, $rootScope, $location, userService, Data, UserDetails) {

            $scope.data = Data;
            $scope.UserDetails = UserDetails;
            initTable();
            function initTable() {
                $scope.user = null;
                if ($scope.data.numOfUsers > 0) {
                    userService.fetchAllUsers($scope.data.currentPage, $scope.data.numOfUsers, null).success(function (response) {
                        $scope.data.users = response.content;
                        console.log(response);
                        $scope.currentPage = response.number;
                        $scope.data.viewUsers = $scope.data.users;
                        $scope.data.numOfPages = response.totalPages - 1;
                        $scope.data.totalItems = response.totalElements;
                    });
                }
            }

            function searchData() {
                $scope.user = null;
                console.log($scope.query);
                userService.fetchAllUsers($scope.data.currentPage, $scope.data.numOfUsers, 'firstName', $scope.query).success(function (response) {
                    $scope.data.users = response.content;
                    console.log(response);
                    $scope.currentPage = response.number;
                    $scope.data.viewUsers = $scope.data.users;
                    $scope.data.numOfPages = (response.totalPages) - 1;
                    $scope.data.totalItems = response.totalElements;
                });
            }
            $scope.search = function () {
                searchData();
            };

            $scope.userForm = function () {
                userService.createUserForm().then(function (user) {
                    $scope.UserDetails.user = user;
                    $scope.user = $scope.UserDetails.user;
                    console.log('User form created: ' + user);
                    return $scope.user;
                });
                $location.path('/add')
            };

            $scope.updateView = function () {
                //$scope.currentPage = currentPage;
                console.log($scope.data.currentPage);

                if ($scope.query == null) {
                    initTable();
                    console.log("inside table init: " + $scope.data.currentPage);
                } else {
                    searchData();
                    console.log("inside search:" + $scope.data.currentPage);

                }

            };

            $scope.setPage = function (pageNo) {
                $scope.data.currentPage = pageNo;
                $scope.user = null;

                $scope.updateView();

            };

            $scope.showDetails = function (id) {
                userService.fetchUserById(id).then(
                    function (response) {
                        $scope.UserDetails.user = response.data;
                        console.log(response);
                    });
                $location.path('/users/' + id);
            }
        }])

    .controller('viewUserController', ['$scope', '$routeParams', 'userService',
        '$location', function ($scope, $routeParams, userService, $location) {
            $scope.id = $routeParams.id;
            showDetails($scope.id);
            function showDetails(id) {
                userService.fetchUserById(id).then(
                    function (response) {
                        $scope.user = response.data;
                        console.log(response);
                    });
            };

            this.back = function () {
                $location.path('/users')
            };

            this.delete = function (id) {
                console.log('id to be deleted', id);
                userService.deleteUser(id).then(
                    $location.path('/users'),
                    function (errResponse) {
                        console.error(errResponse + 'Error while deleting User.');
                    }
                )
            };

            this.edit = function (id) {
                console.log('id to be edited', id);
                for (var i = 0; i < self.users.length; i++) {
                    if (self.users[i].id === id) {
                        self.user = angular.copy(self.users[i]);
                        break;
                    }
                }
            };
        }])

    .controller('editUserController', ['$scope', '$route', '$location', 'userService', 'UserDetails', function ($scope, $route, $location, userService, UserDetails) {
        var self = this;
        self.user = {
            id: null,
            firstName: '',
            lastName: '',
            gender: '',
            phoneNumber: '',
            address: '',
            email: '',
            version: ''
        };
        self.users = [];

        self.back = function () {
            $location.path('/users')
        };

        self.updateUser = function (user, id) {
            userService.updateUser(user, id)
                .then(
                    UserDetails.user = user,
                    function (errResponse) {
                        console.error(errResponse + 'Error while updating User.');
                    }
                );
        };
        self.createUser = function (user) {
            userService.createUser(user).then(
                UserDetails.user = user,
                function (errResponse) {
                    console.error(errResponse + 'Error while creating User.');
                }
            );
            window.location.reload();
        };
        self.edit = function (id) {
            console.log('id to be edited', id);
            for (var i = 0; i < self.users.length; i++) {
                if (self.users[i].id === id) {
                    self.user = angular.copy(self.users[i]);
                    break;
                }
            }
        };
        self.reset = function () {
            self.user = {
                id: null,
                firstName: '',
                lastName: '',
                gender: '',
                phoneNumber: '',
                address: '',
                email: '',
                version: ''
            };
            $scope.userForm.$setPristine(); //reset Form
        };

        self.submit = function () {
            if (self.user.id === null) {
                console.log('Saving New User', self.user);
                self.createUser(self.user);
            } else {
                self.updateUser(self.user, self.user.id);
                console.log('User updated with id ', self.user.id);
            }
            $location.path('/');
            self.reset();
        };
    }]);