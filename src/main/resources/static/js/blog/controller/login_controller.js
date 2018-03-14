'use strict';

angular.module('myApp').controller('LoginController', ['$scope', 'LoginService',function($scope,LoginService) {
    var self = this;
    $scope.userName = localStorage.getItem('username');
    $scope.formData = {
        username: '',
        password: ''
    };


    function LoginUsers(user){
        LoginService.LoginUsers(user)
            .then(
                function(userInfo) {
                    localStorage.setItem('username',userInfo.username);
                    localStorage.setItem('userId',userInfo.id);
                    location.href='/index';
                },
                function(errResponse){
                    UIkit.modal.alert('用户名或密码错误')
                    //Materialize.toast('用户名或密码错误', 2000)
                    console.log("登录密码错误")
                }
            );
    }

    $scope.LoginUsers = function(){
        var user = $scope.formData;
        LoginUsers(user);
    }
}]);