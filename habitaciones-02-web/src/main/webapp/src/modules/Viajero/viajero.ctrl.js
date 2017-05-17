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
            
            $scope.viajero = {};
            
            $scope.update = function(){
                
                if($scope.viajero.contrasenaVer === $scope.currentViajero.contrasena){
                    
                    $http.put('api/viajeros/'+ $stateParams.viajeroId, $scope.currentViajero)
                    .then(function(data){
                        $state.go('viajeroDetail',{viajeroId: $stateParams.viajeroId});
                        console.log(data);
                    }).catch (function(error){
                        document.getElementById('errorUpdate').innerHTML = error.data;
                        console.log('se genero este error: ' + error.data);
                    });
                }
                else
                {
                    document.getElementById('errorUpdate').innerHTML = '*No se pudo '
                    + 'realizar la acutalizacion debido a que la contraseña ingresada '
                    + 'no coincide con la contraseña de la cuenta!';
                }
                
            };
            
            $scope.cambiarContrasena = function(){
                
                if($scope.viajero.contrasenaVer === $scope.currentViajero.contrasena){
                    
                    if($scope.viajero.contrasenaUno === $scope.viajero.contrasenaDos)
                    {
                        $scope.currentViajero.contrasena = $scope.viajero.contrasenaUno;
                        
                        $http.put('api/viajeros/'+ $stateParams.viajeroId, $scope.currentViajero)
                            .then(function(data){
                                $state.go('viajeroDetail',{viajeroId: $stateParams.viajeroId});
                                console.log(data);
                            }).catch (function(error){
                                document.getElementById('errorUpdate').innerHTML = error.data;
                                console.log('se genero este error: ' + error.data);
                            });
                    }
                    else
                    {
                        document.getElementById('errorUpdate').innerHTML = '*No se pudo '
                        + 'realizar el cambio de contraseña debido que estas '
                        + 'no coinciden entre si!';
                    }
                    
                }
                else
                {
                    document.getElementById('errorUpdate').innerHTML = '*No se pudo '
                    + 'realizar la acutalizacion debido a que la contraseña ingresada '
                    + 'no coincide con la contraseña de la cuenta!';
                }
                
            };
            
        }
    ]);
    
    mod.controller("listReservControl", ['$scope', 'listaReservas',
        function($scope, listaReservas)
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
    
    mod.controller("viajeroDetailCtrl", ['$scope', '$http', '$stateParams', 'currentViajero', '$state',
        function ($scope, $http, $stateParams, currentViajero, $state)
        {
            $scope.currentViajero = currentViajero.data;
            
            $scope.deleteViajero = function (){
                $http.delete('api/viajeros/'+ $stateParams.viajeroId)
                    .then(function(data){
                        $state.go('viajerosList');
                        console.log(data);
                    }).catch (function(error){
                        document.getElementById('errorDeleteViajero').innerHTML = 'No se ha podido eliminar '
                        + 'su cuenta debido a que aun tiene reservas pendientes!';
                        console.log('se genero error al eliminar el cliente!' );
                    });
            };
        }
    ]);
    
    
    

})(window.angular);