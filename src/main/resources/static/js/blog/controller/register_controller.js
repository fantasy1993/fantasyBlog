'use strict';

angular.module('myApp').controller('RegisterController', ['$scope', 'RegisterService','$interval',function($scope,RegisterService,$interval) {
    var self = this;
    $scope.formData = {
        userName: '',
        password: '',
        email: '',
        rePassword:''
    };


    function registerUsers(user){
        RegisterService.registerUsers(user)
            .then(
                function(userInfo) {
                    localStorage.setItem('userName',userInfo.userName);
                    localStorage.setItem('userId',userInfo.userId);
                    location.href = "/login";
                },
                function(errResponse){
                    console.error('Error while fetching Articles');
                }
            );
    }

    $scope.registerUsers = function(){
        var user = $scope.formData;
        registerUsers(user);
    }

    $scope.canClick=false;
    $scope.description = "获取验证码";
    var second=59;

    $scope.getTestCode = function() {
        var timerHandler = $interval(function () {
            if (second <= 0) {
                $interval.cancel(timerHandler); //当执行的时间59s,结束时，取消定时器 ，cancle方法取消
                second = 59;
                $scope.description = "获取验证码";
                $scope.canClick = false;   //因为 ng-disabled属于布尔值，设置按钮可以点击，可点击发送
            } else {
                $scope.description = second + "s后重发";
                second--;
                $scope.canClick = true;
            }
        }, 1000, 100);  //每一秒执行一次$interval定时器方法
    };


}]);