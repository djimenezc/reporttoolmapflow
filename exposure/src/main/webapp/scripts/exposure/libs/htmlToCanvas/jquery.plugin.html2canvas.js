/**
 @license html2canvas v0.30 <http://html2canvas.hertzen.com>
 Copyright (c) 2011 Niklas von Hertzen. All rights reserved.
 http://www.twitter.com/niklasvh

 Released under MIT License
 */
/*
 * jQuery helper plugin for examples and tests
 */

(function($) {
    $.fn.html2canvas = function(options, fileType) {

        var $canvas = $('#exposure-map');
        // Original element with attached data

        hideElements();
        canvg();

        html2canvas.logging = options && options.logging;
        html2canvas.Preload(this[0], $.extend({
            complete : function(images) {
                var queue = html2canvas.Parse(this[0], images, options);
                $canvas = $(html2canvas.Renderer(queue, options)), finishTime = new Date();

                var datasets = getDatasets();
                if(datasets != '') {
                    var points = extractPointsFromShape(shape);
                } else {
                    Ext.Msg.alert('No Datasets Selected', 'Please select at least one Dataset');
                }

                var oCanvas = $canvas.context;

                // restore shape and layers
                restoreMap();

                var dataUrl = oCanvas.toDataURL();

                var oImgPNG = Canvas2Image.saveAsPNG(oCanvas, true);

                var uid = '1';
                target = "_blank";

                var xConer = $('#exposure-map').offset().left;
                var yConer = $('#exposure-map').offset().top;

                var height = $('#exposure-map').height();
                var width = $('#exposure-map').width();

                try {
                    post(exportURL, {
                        dataImage : oImgPNG.src,
                        type : fileType,
                        shapeArray : points,
                        shapeType : shapeType,
                        policyType : getExposurePolicyType(),
                        area: getExposureArea(),
                        criteria : gatherExposureCriteria(),
                        clientId : client,
                        datasets : datasets,
                        xConer : xConer,
                        yConer : yConer,
                        height : height,
                        width : width
                    }, target);

                } catch (err) {
                    console.log("Error when call "+exportURL+': '+err);
                    masks['exportMask'].hide();
                    Ext.Msg.alert('Popup Blocker Active', 'Please disable your Popup Blocker to allow PDF export.');
                }

            }
        }, options));

        function hideElements() {
            if(distanceWin != undefined) {
                distanceWin.hide();
                togglePolygonMeasure(false);
            }

            $('.olControlPanZoomBar').hide();
            $('.olControlLayerSwitcher').hide();
            $('.maximizeDiv').hide();
        }

        function showElements() {
            $('.olControlPanZoomBar').show();
            $('.maximizeDiv').show();
        }

        function restoreMap() {

            showElements();
            var container = $('#exposure-map').parent();

            var center = map.getCenter();
            var zoom = map.getZoom();

            $('#exposure-map').children().remove();

            var proj = new OpenLayers.Projection("EPSG:4326");
            map = initMap(map.getCenter(), map.getZoom(), 'exposure-map');

            map.setCenter(center, zoom);

            if(shape) {
                vectors.addFeatures([shape]);
            }

            map.addLayers(storedDataLayers);

            modifyMapControls(map);
        }

        function throwMessage(msg, duration) {
            window.clearTimeout(timeoutTimer);
            timeoutTimer = window.setTimeout(function() {
                $message.fadeOut(function() {
                    $message.remove();
                });
            }, duration || 2000);
            if($message)
                $message.remove();
            $message = $('<div />').html(msg).css({
                margin : 0,
                padding : 10,
                background : "#000",
                opacity : 0.7,
                position : "fixed",
                top : 10,
                right : 10,
                fontFamily : 'Tahoma',
                color : '#fff',
                fontSize : 12,
                borderRadius : 12,
                width : 'auto',
                height : 'auto',
                textAlign : 'center',
                textDecoration : 'none'
            }).hide().fadeIn().appendTo('body');
        }

    };
})(jQuery);
