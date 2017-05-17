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
                    }).catch (function(error){
                        document.getElementById('errorCreateResena').innerHTML = error.data;
                        console.log('se genero este error: ' + error.data);
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
    
    mod.controller("resenaViajeroListCtrl", ['$scope', '$http', '$stateParams', 'resenas',
        function ($scope, $http, $stateParams, resenas)
        {
            $scope.RecordsResena = resenas.data;
            
            $scope.filtrar = function(){
                
                var min = document.getElementById("listVRMin").value;
                var max = document.getElementById("listVRMax").value;
                
                $http.get('api/resenas/porViajero/'+$stateParams.viajeroId + '/'
                            + min + '/'
                            + max)
                    .then(function(lista){
                        
                        $scope.RecordsResena = lista.data;
                });
            };
        }]);
    
    mod.controller("resenaDetailViajeroCtrl", ['$scope', 'currentResena', '$http', '$stateParams', '$state',
        function ($scope, currentResena, $http, $stateParams, $state) 
        {
            $scope.currentResena = currentResena.data; 
            
            $scope.deleteResena = function(){
                
                $http.delete('api/resenas/'+ $stateParams.resenaId)
                    .then(function(data){
                        $state.go('viajeroDetail',{viajeroId: $stateParams.viajeroId});
                        console.log(data);
                    });
            };

        }]
    );
    
})(window.angular);