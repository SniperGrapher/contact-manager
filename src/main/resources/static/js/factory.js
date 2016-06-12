angular.module(contactManagerApp + '.controllers')
    .factory('Data', function () {
        return {
            currentPage: 0,
            numOfUsers: 10,
            numOfPages: 0,
            maxSize: 10,
            totalItems: 0,
            users: {},
            viewUsers: {}
        };
    }).factory('UserDetails', function () {
    return {
        user: {
            id: null,
            firstName: '',
            lastName: '',
            gender: ''
            , phoneNumber: '',
            address: '',
            email: '',
            version: ''
        }
    };
});
