(function (ng) {
    var mod = ng.module("resenaModule");


    mod.controller("resenaCreateCtrl", ['$scope', '$http', '$stateParams', '$state',
        function ($scope, $http, $stateParams, $state) 
        {
            $scope.resena = {
                viajero: {
                    idUsuario: $stateParams.viajeroId
                },
                habitacion: {
                    id: $stateParams.habitacionId
                }
            };
            
            $scope.crearResena = function () {
                
                $http.post('api/resenas', $scope.resena)
                    .then(function (data) {
                        $state.go('viajeroDetail',{viajeroId: $stateParams.viajeroId});
                        console.log(data);
                    });
            };
        }]
    );
    
    mod.controller("resenaUpdateCtrl", ['$scope', '$http', '$stateParams', '$state',
        function ($scope, $http, $stateParams, $state) 
        {
            
            $scope.updateResena = function(){
                
                $http.put('api/resenas/'+ $stateParams.resenaId, $scope.currentResena)
                    .then(function(data){
                        $state.go('resenaDetailViajero',{resenaId: $stateParams.resenaId});
                        console.log(data);
                    });
            };
        }]
    );
    
    mod.controller("resenaListCtrl", ['$scope', 'resenas',
        function ($scope, resenas)
        {
            $scope.RecordsResena = resenas.data;
        }]);
})(window.angular);