var app = angular.module('myApp',['ngTable']);
app.factory('TableService',['$http','$q',function($http,$q){
    var REST_SERVICE_URI = 'http://localhost:8080/article/';
    var factory = {
        loadPermission: loadPermission
    };


    return factory;

    function loadPermission() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
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