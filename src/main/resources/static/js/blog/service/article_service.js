'use strict'
angular.module('myApp').factory('ArticleService',['$http','$q','$rootScope',function($http,$q){
	var REST_SERVICE_URI = '/article/';
    var REST_SAVE_COMMENT = '/saveComment/';
	var factory = {
			loadArticles: loadArticles,
            saveArticle: saveArticle,
            loadComments: loadComments,
            comments: comments,
            deleteArticle: deleteArticle
	};
	

	return factory;

    function comments(comments){
        var deferred = $q.defer();
        $http.post(REST_SAVE_COMMENT,comments)
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

    function deleteArticle(articleId){
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+articleId)
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


	function loadArticles() {
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

	function loadComments(articleId){
        var deferred = $q.defer();
        $http.get('/comment/'+articleId)
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

    function saveArticle(article) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI,article)
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