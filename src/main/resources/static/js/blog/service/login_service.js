'use strict'
angular.module('myApp').factory('LoginService',['$http','$q',function($http,$q){
    var REST_SERVICE_URI = '/validateLogin';
    var factory = {
        LoginUsers: LoginUsers


    };
    return factory;

    function LoginUsers(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI,user)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating user');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);