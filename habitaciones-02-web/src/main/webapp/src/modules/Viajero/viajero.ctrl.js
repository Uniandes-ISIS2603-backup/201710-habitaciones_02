(function (ng) {
    var mod = ng.module("viajeroModule");

    mod.controller("viajeroCreateCtrl", ['$scope', '$http', 
        function ($scope, $http) 
        {
            
            $scope.viajero = {};
            $scope.signup = function () {
                $http.post('api/viajeros', $scope.viajero)
                    .then(function (data) {
                        console.log(data);
                    });
            };
        }]
    );
    
    mod.controller("viajeroUpdateCtrl", ['$scope', '$http','currentViajero','$stateParams', 
        function($scope, $http, currentViajero, $stateParams ){
            
            $scope.update = function(){
                
                console.log(currentViajero);
                
                $http.put('api/viajeros/'+ $stateParams.viajeroId, currentViajero)
                    .then(function(data){
                        console.log(data);
                    });
            };
            
        }
    ]);
    
    mod.controller("listReservControl", ['$scope', '$http','currentViajero',
        function($scope, $http, currentViajero){
            
            $scope.rango = {};
            $scope.update = function(){
                
            };
            
        }
    ]);

    mod.controller("viajeroListCtrl", ['$scope', 'viajeros',
        function ($scope, viajeros)
        {
            $scope.RecordsViajero = viajeros.data;
        }
    ]);

})(window.angular);