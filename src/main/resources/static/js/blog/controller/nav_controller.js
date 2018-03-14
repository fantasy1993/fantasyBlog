angular.module('myApp')
    .controller('NavController', function($scope, $location) {

        // Public.
        // "navItems"存储着导航栏的栏目。
        $scope.navItems = [
            {
                title: '首页',
                locationUrl: '/index',
                href: '/index'
            },
            {
                title: '文章',
                locationUrl: '/article',
                href: '/article'
            },
            {
                title: '写文章',
                locationUrl: '/writeArticles',
                href: '/writeArticles'
            },
        ]
        // .selectedNavItem变量存储当前选择项，默认的选择项是"Home"。
        $scope.selectedNavItem = '首页'
        // 栏目click时触发的方法。
        $scope.itemClick = function(itemTitle) {
            $scope.selectedNavItem = itemTitle
        }
        // 初始化。
        // 判断当前地址栏路径属于哪个导航栏目。
        var currentLocation = $location.path()
        for (var i = 0, len = $scope.navItems.length; i < len; i++) {
            var navItem = $scope.navItems[i]
            if (currentLocation == navItem.locationUrl) {
                $scope.selectedNavItem = navItem.title
            }
        }
    });
