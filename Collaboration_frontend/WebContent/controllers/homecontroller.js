/**
 * 
 */

app.controller('HomeController',function(BlogPostService,$rootScope,$location){
	function getNotification(){
		BlogPostService.getNotification().then(function(response){
			$rootScope.blogApprovalStatus=response.data	//list of blogpost
			$rootScope.approvalStatusLength=$rootScope.blogApprovalStatus.length //no of objects
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$rootScope.updateViewedStatus=function(blogPost){
		blogPost.viewed=1
		BlogPostService.updateBlogPost(blogPost).then(function(response){
			getNotification();
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$rootScope.updateLength=function()
	{
		$rootScope.approvalStatusLength=0
	}
	
	getNotification()
})