angular.module('myApp').directive('timerbutton',function($timeout,$interval){
    return {
        restrict: 'AE',
        scope: {
            showTimer: '=',
            timeout: '='
        },
    link:  function(scope,element,attrs) {
        scope.timer = false;
        scope.timerout = 60000;
        scope.timerCount = scope.timerout / 1000
        scope.text = "获取验证码";

        scope.onClick = function () {
            scope.showtimer = true;
            scope.timer = true;
            scope.text = "秒后重新获取";
            var counter = $interval(function () {
                scope.timerCount = scope.timerCount - 1;
            }, 1000);

            $timeout(function () {
                scope.text = "获取验证码";
                scope.timer = false;
                $interval.cancel(counter);
                scope.showTimer = false;
                scope.timerCount = scope.timeout / 1000;
            }, scope.timeout);
        }
    },
        template: '<button type="button" on-tap="onClick()" class="btn btn-success btn-lg" ng-disabled="timer">' +
        '<span ng-if="showTimer">{{ timerCount }}</span>{{text}}</button>'
    };
});