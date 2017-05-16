(function (ng) {
    var mod = ng.module("viajeroModule");

    mod.controller("viajeroCreateCtrl", ['$scope', '$http', '$state',
        function ($scope, $http, $state) 
        {
            
            $scope.viajero = {};
            $scope.signup = function () {
                
                $http.post('api/viajeros', $scope.viajero)
                    .then(function (data) {
                        
                        $state.go('viajeroDetail',{viajeroId: data.data.idUsuario});
                        console.log(data);
                    });
            };
        }]
    );
    
    mod.controller("viajeroUpdateCtrl", ['$scope', '$http','$stateParams', '$state',
        function($scope, $http, $stateParams, $state ){
            
            $scope.update = function(){
                
                $http.put('api/viajeros/'+ $stateParams.viajeroId, $scope.currentViajero)
                    .then(function(data){
                        $state.go('viajeroDetail',{viajeroId: $stateParams.viajeroId});
                        console.log(data);
                    });
            };
            
        }
    ]);
    
    mod.controller("listReservControl", ['$scope','listaReservas',
        function($scope,  listaReservas, $state)
        {
            $scope.RecordsReservas = listaReservas.data;
        }
    ]);

    mod.controller("viajeroListCtrl", ['$scope', 'viajeros',
        function ($scope, viajeros)
        {
            $scope.RecordsViajero = viajeros.data;
        }
    ]);
    
    
    
    
    

})(window.angular);