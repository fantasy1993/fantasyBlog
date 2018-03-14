'use strict'
angular.module('myApp').factory('RegisterService',['$http','$q',function($http,$q){
    var REST_SERVICE_URI = 'http://localhost:8080/register/';
    var factory = {
        registerUsers: registerUsers
    };


    return factory;

    function registerUsers(user) {
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