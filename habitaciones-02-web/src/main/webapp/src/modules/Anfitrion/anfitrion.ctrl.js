(function (ng) {
    var mod = ng.module("anfitrionModule");
    mod.controller("createAnfitrionCtrl", ['$scope', '$http', 
        function ($scope, $http) {
            $scope.anfitrion = {};
            $scope.anfitrion.imagen = "http://dummyimage.com/335x343.jpg/ff4444/ffffff";
            $scope.signup = function () {
                console.log($scope.anfitrion);
                $http.post('api/anfitriones', $scope.anfitrion)
                        .then(function (data) {
                        $state.go('anfitrionDetail',{anfitrionId: data.data.idUsuario});
                        console.log(data);
                    console.log(data);
                });
            }
        }]
            );
    mod.controller("anfitrionListCtrl", ['$scope', 'anfitriones',
        function ($scope,  anfitriones) {
            
            $scope.anfitrionesRecords = anfitriones.data;
        }

    ]);
     mod.controller("anfitrionUpdateCtrl", ['$scope', '$http','$stateParams', '$state',
        function($scope, $http, $stateParams, $state ){
            
            $scope.update = function(){
                
                $http.put('api/anfitriones/'+ $stateParams.anfitrionId, $scope.currentAnfitrion)
                    .then(function(data){
                        $state.go('anfitrionDetail',{anfitrionId: $stateParams.anfitrionId});
                        
                    });
            };
            
        }
    ]);
    
    
})(window.angular);