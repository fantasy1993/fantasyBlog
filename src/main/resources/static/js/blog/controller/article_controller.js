'use strict';

angular.module('myApp').controller('ArticleController', ['$scope', 'ArticleService','$sce',function($scope,ArticleService,$sce) {
	var self = this;
    //self.article={articleId:null,title:'',postTime:'',content:'',imgUrl:''};
    self.article = [];
    self.articles=[];
    self.comments = [];
    $scope.userName = localStorage.getItem('userName');
    $scope.userId = localStorage.getItem('userId');
    $scope.articleTitle = localStorage.getItem('articleTitle');
    $scope.articleContent = localStorage.getItem('articleContent');
    $scope.postTime = localStorage.getItem('postTime');
    $scope.articleId = localStorage.getItem('articleId');
    $scope.writeTitle = $scope.articleTitle;
    $scope.formData = {
        articleId: '',
        title: '',
        content: '',
        postTime: '',
        imgUrl: ''
    };
    loadEditContent();
    loadArticles();
    loadComments($scope.articleId);

    function loadEditContent(){
        if((typeof simplemd) != "undefined") {
            simplemd.value($scope.articleContent);
        }
    }


    $scope.comments = {
        articleId: localStorage.getItem('articleId'),
        userId: $scope.userId,
        comment: ''
    }

    $scope.deleteArticleId = {
        title: '',
        content: '',
        postTime: '',
        imgUrl: '',
        articleId: $scope.articleId
    }

    $scope.subComment = function() {
        $scope.comments.comment =  $scope.commentContent;
        var comments = $scope.comments;
        ArticleService.comments(comments)
            .then(
                function (d) {
                    UIkit.modal.alert('评论成功');
                },
                function (errResponse) {
                    UIkit.modal.alert('评论失败');
                }
            );
    }
    
    function loadArticles(){
            ArticleService.loadArticles()
                .then(
                    function (d) {
                        if(d.code == -1){
                            UIkit.modal.alert(d.msg);
                        }
                        self.articles = angular.copy(d.data);
                        self.article = angular.copy(d.data);
                        var contents;
                        for(var i in self.articles){
                            contents = self.articles[i].content;
                            contents= contents.substring(0,100);
                            self.articles[i].content = $sce.trustAsHtml(markdown.toHTML(contents))
                        }
                    },
                    function (errResponse) {
                        console.error('Error while fetching Articles');
                    }
                );
    }

    $scope.loadArticle =  function(articleId) {
        for(var i in self.article) {
            if (self.article[i].articleId == articleId) {
                localStorage.setItem('articleId',self.article[i].articleId);
                localStorage.setItem('articleTitle',self.article[i].title);
                localStorage.setItem('articleContent', self.article[i].content);
                localStorage.setItem('postTime', self.article[i].postTime);
            }
        }
        location.href='/articleContent';
    }
    
    $scope.backArticle = function () {
        location.href = '/article';
    }

    $scope.deleteArticle = function () {
        UIkit.modal.confirm('是否删除文章？').then(function () {
            ArticleService.deleteArticle($scope.articleId)
                .then(
                    function(d) {
                        UIkit.modal.alert('删除成功');
                        location.href='/article';
                    },
                    function(errResponse){
                        UIkit.modal.alert('删除失败');
                    }
                );
        }, function () {
            console.log('不删除.')
        });

    }

    function loadComments(articleId){
        ArticleService.loadComments(articleId)
            .then(
                function(d) {
                    self.comments = d;
                    console.log("success");
                },
                function(errResponse){
                    console.error('Error while fetching Articles');
                }
            );
    }

    function saveArticle(article) {
        ArticleService.saveArticle(article)
            .then(
                function(d) {
                    self.articles = d;
                    UIkit.modal.alert('发布成功');
                    location.href='/article';
                },
                function(errResponse){
                    console.error('Error while fetching Articles');
                }
            );
    }


    $scope.postArticle = function(){
        $scope.formData.content = simplemde.value();
        $scope.formData.userId = $scope.userId;
        $scope.formData.title = $scope.title;
        var article = $scope.formData;
        saveArticle(article);
    }

    $scope.editArticle = function(){
        $scope.formData.content = simplemd.value();
        $scope.formData.userId = $scope.userId;
        $scope.formData.articleId = $scope.articleId;
        $scope.formData.title = $scope.writeTitle;
        var article = $scope.formData;
        saveArticle(article);
    }



    $scope.content = function(){
        return  $sce.trustAsHtml(markdown.toHTML($scope.articleContent));
    }



}]);