/**
 * 
 */
app.controller('BlogPostController',function($scope,BlogPostService,$location){
	
	$scope.addBlogPost=function(){
		BlogPostService.addBlogPost($scope.blog).then(function(response){
			alert('BlogPost added successfully and waiting for approval..')
			$location.path('/home')
		},function(response){
			$scope.error=response.data.message
			if(response.status==401)
				$location.path('/login')
			else //500
				$location.path('/addblogpost')
		})
	}
	
	/*
	 *  list of blogs are approved
	 * */
	function blogsApproved(){
		BlogPostService.blogsApproved().then(function(response){
			$scope.listOfBlogsApproved=response.data //list<blogpost> approved
		},function(response){
			
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	
	/*
	 * list of blogs waiting for approval
	 * */
	function blogsWaitingForApproval(){
		BlogPostService.blogsWaitingForApproval().then(function(response){
			
			$scope.listOfBlogsWaitingForApproval=response.data //list<blogpost> waiting for approval
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	blogsApproved()	 //select * from blogpost where approved =1
	blogsWaitingForApproval()	//select * from blogpost where approved - 0
	
})
