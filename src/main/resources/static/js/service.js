angular.module(contactManagerApp + '.services')
// .service('userService', ['$http', function ($http) {
//     this.findAllUsers = function (page, size, search) {
//         return $http({
//             method: 'GET',
//             url: '/users',
//             params: {
//                 page: page,
//                 size: size,
//                 search: search
//             }
//         });
//     }
//     this.searchUser = function (page, size, search) {
//         return $http({
//             method: 'GET',
//             url: '/users',
//             params: {
//                 page: page,
//                 size: size,
//                 search: search
//             }
//         });
//     }
//     this.updateUser = function(user, id){
//         return $http.put('/users/'+id, user)
//             .then(
//                 function(response){
//                     return response.data;
//                 },
//                 function(errResponse){
//                     console.error('Error while updating user');
//                     return $q.reject(errResponse);
//                 }
//             );
//     },
//     this.findById = function (id) {
//         return $http({
//             method: 'GET',
//             url: '/users',
//             params: {
//                 id: id
//             }
//         });
//     }
//    
//     this.newUserForm = function () {
//         return $http({
//             method: 'GET',
//             url: '/users?form'
//         });
//     }
//     this.createUser = function (user) {
//         return $http({
//             method: 'POST',
//             data: user,
//             url: '/users?form'
//         });
//     }
//     this.deleteUser = function (id) {
//         return $http({
//             method: 'DELETE',
//             url: '/users',
//             params: {
//                 id: id
//             }
//         });
//     }
    .factory('userService', ['$http', '$q', function ($http, $q) {

        return {

            fetchAllUsers: function (page, size, sort, search) {
                return $http({
                    method: 'GET',
                    url: '/users',
                    params: {
                        page: page,
                        size: size,
                        sortBy: sort,
                        search: search
                    }
                });
            },
            fetchUserById: function (id) {
                return $http({
                    method: 'GET',
                    url: '/users/' + id
                })
            },

            createUserForm: function () {
                return $http({
                    method: 'GET',
                    url: '/users?form'
                });
            },

            createUser: function (user) {
                return $http.post('/users?form', user)
                    .then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while creating user');
                            return $q.reject(errResponse);
                        }
                    );
            },

            updateUser: function (user, id) {
                return $http.put('/users' + id + '/?form', user)
                    .then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while updating user');
                            return $q.reject(errResponse);
                        }
                    );
            },

            deleteUser: function (id) {
                return $http.delete('/users/' + id)
                    .then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting user');
                            return $q.reject(errResponse);
                        }
                    );
            }

        };

    }]);