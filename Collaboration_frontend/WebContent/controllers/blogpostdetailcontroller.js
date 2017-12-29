/**
 * 
 */

app.controller('BlogPostDetailController',function($scope,$location,BlogPostService,$routeParams){
	$scope.isRejected=false
	$scope.isLiked=false
	
	var id=$routeParams.id
	
	BlogPostService.getBlogPostById(id).then(function(response) {
		$scope.blogPost=response.data // Blogpost [select * from blogpost where id =?]
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	/*
	 * upadte approved /rejection reson in blogPost table
	 * update blogPost set approved =1 and where id=?
	 * 
	 * r
	 * update blogpost set rejectionresaon 
	 * 
	 * */
	
	$scope.updateBlogPost=function(){
		console.log("Approved/ Rejected" + $scope.blogPost.approved)
		BlogPostService.updateBlogPost($scope.blogPost).then(function(response){
			$location.path('/getblogs')
		},function(response){
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.updateLikes=function(){
		$scope.isLiked=!$scope.isLiked;
		if($scope.isLiked){
			$scope.blogPost.likes=$scope.blogPost.likes + 1 	// 1st click
		}
		else {
			$scope.blogPost.likes=$scope.blogPost.likes - 1	//2nd click
		}
		//update blogPost set likes=? where id=?
		BlogPostService.updateBlogPost($scope.blogPost).then(function(response){},function(response){})
	}
	
	
	$scope.showRejectionTxt=function(val){
		$scope.isRejected=val
	}
	
	$scope.addComment=function(){	
		console.log($scope.blogComment) // commentext property in blogcomment
		$scope.blogComment.blogPost=$scope.blogPost // blogpost property in blog comment
		console.log($scope.blogComment)
		BlogPostService.addComment($scope.blogComment).then(function(response){
			console.log(response.data)
			$scope.blogComment.commentText=''
			getBlogComments();
		},function(response){
			if(response.status==401)
				$location.path('/login') //login.html
			else 
				$loaction.path('/getblogbyid/'+id) //blogdetails.html
		})
	}
	
	function getBlogComments(){	//select blogcomment for particular blogpost
		BlogPostService.getBlogComments(id).then(function(response){
			$scope.blogComments=response.data	//list of blogcomments for id
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	getBlogComments()
	
	
})