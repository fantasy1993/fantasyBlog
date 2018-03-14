var app = angular.module('myApp',['ngTable']);

app.controller("tableController", ["$scope","$http","NgTableParams",function($scope,$http,NgTableParams) {

    $http.get('/article/').success(function(data) {
        $scope.tableParams = new NgTableParams({
            page: 1,
            count: 20
        }, {
            total: data.length,
            getData: data
        })
    });
}]);





