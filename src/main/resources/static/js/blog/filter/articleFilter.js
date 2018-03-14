
angular.module('myApp').filter('fantasyFilter', function(){
        return function (articleContent,limit) {
            if(articleContent.length > limit){
                return articleContent.substring(0,limit) + "<a href='#'>...</a>";
            }

            return articleContent;
        }
});
