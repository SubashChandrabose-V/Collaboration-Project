/**
 * 
 */


app.controller('JobController',function($scope,$location,JobService){
	$scope.showJobDetails=false;
	$scope.addJob=function(){
		JobService.addJob($scope.job).then(function(response){
			console.log(response.data)
			console.log(response.status)
			getAllJobs()
			$location.path('/getalljobs')
		},function(response){
			
			console.log(response.data)
			console.log(response.status)
			$scope.errorMsg=response.data.message
			if(response.status==401)
			{
				$location.path('/login')
			}
			else {
				$location.path('/addjob')
			}
			
		})
	}
	
	$scope.getJobDetails=function(jobId){
		$scope.showJobDetails=true
		JobService.getJobDetails(jobId).then(function(response){
			$scope.job=response.data	// job object results of the query - select * from job where id=jobid
		},function(response){
			console.log(response.data)
			if(response.status==401)
				{
				$location.path('/login')
				}
		})
	}
	
	function getAllJobs()
	{
		JobService.getAllJobs().then(function(response){
			$scope.jobs=response.data
		},function(response) {
			if(response.status==401)
			{
				$location.path('/login')
			}
		})
	}
	getAllJobs() // this stm gets executed automatically whenever the controller get instaniated
})