(function (ng) {
    var mod = ng.module("resenaModule");


    mod.controller("resenaCreateCtrl", ['$scope', '$http', '$stateParams', '$state',
        function ($scope, $http, $stateParams, $state) 
        {
            $scope.currentStar = 0;
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

            $scope.overStar = function (s) {
                $scope.resetStars();
                    for (var i = 1; i <= s; i++) {
                        document.getElementById('star' + i).style.color = '#dcb40a';
                    }
                
            };
            $scope.leaveStar = function (s) {
                
                if(s > $scope.currentStar){
                    for (var i = s; i > $scope.currentStar; i--) {
                        document.getElementById('star' + i).style.color = '#212121';
                    }
                }
                else{
                    $scope.overStar($scope.currentStar);
                }
                
                
            };
            $scope.setDefaultStar = function (s) {
                $scope.resena.calificacion = s;
                $scope.currentStar = s;
                $scope.showSelectedStar();
            };
            
            $scope.showSelectedStar = function () {
                if ($scope.currentStar  > 0) {
                    $scope.overStarSelect($scope.currentStar );
                }
            };
            
            $scope.overStarSelect = function (s){
                for (var i = 1; i <= s; i++) {
                    document.getElementById('star' + i).style.color = '#dcb40a';
                }
            };
            
            $scope.resetStars = function () {
                for (var i = 5; i > 0; i--) {
                    document.getElementById('star' + i).style.color = '#212121';
                }
            };
            
        }]
    );
    
    mod.controller("resenaUpdateCtrl", ['$scope', '$http', '$stateParams', '$state',
        function ($scope, $http, $stateParams, $state) 
        {
            $scope.estrella = $scope.currentResena.calificacion;
            
            $scope.updateResena = function(){
                
                $http.put('api/resenas/'+ $stateParams.resenaId, $scope.currentResena)
                    .then(function(data){
                        $state.go('resenaDetailViajero',{resenaId: $stateParams.resenaId});
                        console.log(data);
                    });
            };
            
            $scope.overStar = function (s) {
                $scope.resetStars();
                    for (var i = 1; i <= s; i++) {
                        document.getElementById('star' + i).style.color = '#dcb40a';
                    }
                
            };
            $scope.leaveStar = function (s) {
                
                if(s > $scope.estrella){
                    for (var i = s; i > $scope.estrella; i--) {
                        document.getElementById('star' + i).style.color = '#212121';
                    }
                }
                else{
                    $scope.overStar($scope.estrella);
                }
                
                
            };
            $scope.setDefaultStar = function (s) {
                $scope.currentResena.calificacion = s;
                $scope.estrella = s;
                $scope.showSelectedStar();
            };
            
            $scope.showSelectedStar = function () {
                if ($scope.estrella  > 0) {
                    $scope.overStarSelect($scope.estrella );
                }
            };
            
            $scope.overStarSelect = function (s){
                for (var i = 1; i <= s; i++) {
                    document.getElementById('star' + i).style.color = '#dcb40a';
                }
            };
            
            $scope.resetStars = function () {
                for (var i = 5; i > 0; i--) {
                    document.getElementById('star' + i).style.color = '#212121';
                }
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