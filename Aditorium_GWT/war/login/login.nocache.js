function login(){
  var $intern_0 = '', $intern_29 = '" for "gwt:onLoadErrorFn"', $intern_27 = '" for "gwt:onPropertyErrorFn"', $intern_12 = '"><\/script>', $intern_14 = '#', $intern_53 = '.cache.html', $intern_16 = '/', $intern_47 = '28CD9B94EA244BB0CF6F92B34EC74D90', $intern_48 = '4A190BFCC7851E39858F62255100AA46', $intern_49 = '5173817F983301C5999C24B5847F7D66', $intern_50 = '9310B1D96AF840445BAE0BA8C4D3E018', $intern_74 = '<script defer="defer">login.onInjectionDone(\'login\')<\/script>', $intern_11 = '<script id="', $intern_56 = '<script language="javascript" src="', $intern_24 = '=', $intern_15 = '?', $intern_51 = 'B4440C576CC007CA9014538E1CBCB029', $intern_26 = 'Bad handler "', $intern_52 = 'D02BCCF3166FB0C15704E0949D58A490', $intern_54 = 'DOMContentLoaded', $intern_13 = 'SCRIPT', $intern_10 = '__gwt_marker_login', $intern_17 = 'base', $intern_4 = 'begin', $intern_3 = 'bootstrap', $intern_19 = 'clear.cache.gif', $intern_23 = 'content', $intern_9 = 'end', $intern_41 = 'gecko', $intern_42 = 'gecko1_8', $intern_5 = 'gwt.codesvr=', $intern_6 = 'gwt.hosted=', $intern_7 = 'gwt.hybrid', $intern_28 = 'gwt:onLoadErrorFn', $intern_25 = 'gwt:onPropertyErrorFn', $intern_22 = 'gwt:property', $intern_45 = 'hosted.html?login', $intern_40 = 'ie6', $intern_39 = 'ie8', $intern_30 = 'iframe', $intern_18 = 'img', $intern_31 = "javascript:''", $intern_44 = 'loadExternalRefs', $intern_1 = 'login', $intern_20 = 'meta', $intern_33 = 'moduleRequested', $intern_8 = 'moduleStartup', $intern_38 = 'msie', $intern_21 = 'name', $intern_35 = 'opera', $intern_32 = 'position:absolute;width:0;height:0;border:none', $intern_37 = 'safari', $intern_68 = 'sc/modules/ISC_Calendar.js', $intern_69 = 'sc/modules/ISC_Calendar.js"><\/script>', $intern_60 = 'sc/modules/ISC_Containers.js', $intern_61 = 'sc/modules/ISC_Containers.js"><\/script>', $intern_55 = 'sc/modules/ISC_Core.js', $intern_57 = 'sc/modules/ISC_Core.js"><\/script>', $intern_70 = 'sc/modules/ISC_DataBinding.js', $intern_71 = 'sc/modules/ISC_DataBinding.js"><\/script>', $intern_64 = 'sc/modules/ISC_Forms.js', $intern_65 = 'sc/modules/ISC_Forms.js"><\/script>', $intern_58 = 'sc/modules/ISC_Foundation.js', $intern_59 = 'sc/modules/ISC_Foundation.js"><\/script>', $intern_62 = 'sc/modules/ISC_Grids.js', $intern_63 = 'sc/modules/ISC_Grids.js"><\/script>', $intern_66 = 'sc/modules/ISC_RichTextEditor.js', $intern_67 = 'sc/modules/ISC_RichTextEditor.js"><\/script>', $intern_72 = 'sc/skins/Enterprise/load_skin.js', $intern_73 = 'sc/skins/Enterprise/load_skin.js"><\/script>', $intern_46 = 'selectingPermutation', $intern_2 = 'startup', $intern_43 = 'unknown', $intern_34 = 'user.agent', $intern_36 = 'webkit';
  var $wnd = window, $doc = document, $stats = $wnd.__gwtStatsEvent?function(a){
    return $wnd.__gwtStatsEvent(a);
  }
  :null, $sessionId = $wnd.__gwtStatsSessionId?$wnd.__gwtStatsSessionId:null, scriptsDone, loadDone, bodyDone, base = $intern_0, metaProps = {}, values = [], providers = [], answers = [], onLoadErrorFunc, propertyErrorFunc;
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_4});
  if (!$wnd.__gwt_stylesLoaded) {
    $wnd.__gwt_stylesLoaded = {};
  }
  if (!$wnd.__gwt_scriptsLoaded) {
    $wnd.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    var result = false;
    try {
      var query = $wnd.location.search;
      return (query.indexOf($intern_5) != -1 || (query.indexOf($intern_6) != -1 || $wnd.external && $wnd.external.gwtOnLoad)) && query.indexOf($intern_7) == -1;
    }
     catch (e) {
    }
    isHostedMode = function(){
      return result;
    }
    ;
    return result;
  }

  function maybeStartModule(){
    if (scriptsDone && loadDone) {
      var iframe = $doc.getElementById($intern_1);
      var frameWnd = iframe.contentWindow;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name){
          return computePropValue(name);
        }
        ;
      }
      login = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, $intern_1, base);
      $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_8, millis:(new Date).getTime(), type:$intern_9});
    }
  }

  function computeScriptBase(){
    var thisScript, markerId = $intern_10, markerScript;
    $doc.write($intern_11 + markerId + $intern_12);
    markerScript = $doc.getElementById(markerId);
    thisScript = markerScript && markerScript.previousSibling;
    while (thisScript && thisScript.tagName != $intern_13) {
      thisScript = thisScript.previousSibling;
    }
    function getDirectoryOfFile(path){
      var hashIndex = path.lastIndexOf($intern_14);
      if (hashIndex == -1) {
        hashIndex = path.length;
      }
      var queryIndex = path.indexOf($intern_15);
      if (queryIndex == -1) {
        queryIndex = path.length;
      }
      var slashIndex = path.lastIndexOf($intern_16, Math.min(queryIndex, hashIndex));
      return slashIndex >= 0?path.substring(0, slashIndex + 1):$intern_0;
    }

    ;
    if (thisScript && thisScript.src) {
      base = getDirectoryOfFile(thisScript.src);
    }
    if (base == $intern_0) {
      var baseElements = $doc.getElementsByTagName($intern_17);
      if (baseElements.length > 0) {
        base = baseElements[baseElements.length - 1].href;
      }
       else {
        base = getDirectoryOfFile($doc.location.href);
      }
    }
     else if (base.match(/^\w+:\/\//)) {
    }
     else {
      var img = $doc.createElement($intern_18);
      img.src = base + $intern_19;
      base = getDirectoryOfFile(img.src);
    }
    if (markerScript) {
      markerScript.parentNode.removeChild(markerScript);
    }
  }

  function processMetas(){
    var metas = document.getElementsByTagName($intern_20);
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name = meta.getAttribute($intern_21), content;
      if (name) {
        if (name == $intern_22) {
          content = meta.getAttribute($intern_23);
          if (content) {
            var value, eq = content.indexOf($intern_24);
            if (eq >= 0) {
              name = content.substring(0, eq);
              value = content.substring(eq + 1);
            }
             else {
              name = content;
              value = $intern_0;
            }
            metaProps[name] = value;
          }
        }
         else if (name == $intern_25) {
          content = meta.getAttribute($intern_23);
          if (content) {
            try {
              propertyErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_26 + content + $intern_27);
            }
          }
        }
         else if (name == $intern_28) {
          content = meta.getAttribute($intern_23);
          if (content) {
            try {
              onLoadErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_26 + content + $intern_29);
            }
          }
        }
      }
    }
  }

  function unflattenKeylistIntoAnswers(propValArray, value){
    var answer = answers;
    for (var i = 0, n = propValArray.length - 1; i < n; ++i) {
      answer = answer[propValArray[i]] || (answer[propValArray[i]] = []);
    }
    answer[propValArray[n]] = value;
  }

  function computePropValue(propName){
    var value = providers[propName](), allowedValuesMap = values[propName];
    if (value in allowedValuesMap) {
      return value;
    }
    var allowedValuesList = [];
    for (var k in allowedValuesMap) {
      allowedValuesList[allowedValuesMap[k]] = k;
    }
    if (propertyErrorFunc) {
      propertyErrorFunc(propName, allowedValuesList, value);
    }
    throw null;
  }

  var frameInjected;
  function maybeInjectFrame(){
    if (!frameInjected) {
      frameInjected = true;
      var iframe = $doc.createElement($intern_30);
      iframe.src = $intern_31;
      iframe.id = $intern_1;
      iframe.style.cssText = $intern_32;
      iframe.tabIndex = -1;
      $doc.body.appendChild(iframe);
      $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_8, millis:(new Date).getTime(), type:$intern_33});
      iframe.contentWindow.location.replace(base + initialHtml);
    }
  }

  providers[$intern_34] = function(){
    var ua = navigator.userAgent.toLowerCase();
    var makeVersion = function(result){
      return parseInt(result[1]) * 1000 + parseInt(result[2]);
    }
    ;
    if (ua.indexOf($intern_35) != -1) {
      return $intern_35;
    }
     else if (ua.indexOf($intern_36) != -1) {
      return $intern_37;
    }
     else if (ua.indexOf($intern_38) != -1) {
      if (document.documentMode >= 8) {
        return $intern_39;
      }
       else {
        var result = /msie ([0-9]+)\.([0-9]+)/.exec(ua);
        if (result && result.length == 3) {
          var v = makeVersion(result);
          if (v >= 6000) {
            return $intern_40;
          }
        }
      }
    }
     else if (ua.indexOf($intern_41) != -1) {
      var result = /rv:([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3) {
        if (makeVersion(result) >= 1008)
          return $intern_42;
      }
      return $intern_41;
    }
    return $intern_43;
  }
  ;
  values[$intern_34] = {gecko:0, gecko1_8:1, ie6:2, ie8:3, opera:4, safari:5};
  login.onScriptLoad = function(){
    if (frameInjected) {
      loadDone = true;
      maybeStartModule();
    }
  }
  ;
  login.onInjectionDone = function(){
    scriptsDone = true;
    $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_44, millis:(new Date).getTime(), type:$intern_9});
    maybeStartModule();
  }
  ;
  computeScriptBase();
  var strongName;
  var initialHtml;
  if (isHostedMode()) {
    if ($wnd.external && ($wnd.external.initModule && $wnd.external.initModule($intern_1))) {
      $wnd.location.reload();
      return;
    }
    initialHtml = $intern_45;
    strongName = $intern_0;
  }
  processMetas();
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_46});
  if (!isHostedMode()) {
    try {
      unflattenKeylistIntoAnswers([$intern_40], $intern_47);
      unflattenKeylistIntoAnswers([$intern_42], $intern_48);
      unflattenKeylistIntoAnswers([$intern_39], $intern_49);
      unflattenKeylistIntoAnswers([$intern_35], $intern_50);
      unflattenKeylistIntoAnswers([$intern_37], $intern_51);
      unflattenKeylistIntoAnswers([$intern_41], $intern_52);
      strongName = answers[computePropValue($intern_34)];
      initialHtml = strongName + $intern_53;
    }
     catch (e) {
      return;
    }
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      maybeStartModule();
      if ($doc.removeEventListener) {
        $doc.removeEventListener($intern_54, onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  if ($doc.addEventListener) {
    $doc.addEventListener($intern_54, function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    if (/loaded|complete/.test($doc.readyState)) {
      maybeInjectFrame();
      onBodyDone();
    }
  }
  , 50);
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_9});
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_44, millis:(new Date).getTime(), type:$intern_4});
  if (!__gwt_scriptsLoaded[$intern_55]) {
    __gwt_scriptsLoaded[$intern_55] = true;
    document.write($intern_56 + base + $intern_57);
  }
  if (!__gwt_scriptsLoaded[$intern_58]) {
    __gwt_scriptsLoaded[$intern_58] = true;
    document.write($intern_56 + base + $intern_59);
  }
  if (!__gwt_scriptsLoaded[$intern_60]) {
    __gwt_scriptsLoaded[$intern_60] = true;
    document.write($intern_56 + base + $intern_61);
  }
  if (!__gwt_scriptsLoaded[$intern_62]) {
    __gwt_scriptsLoaded[$intern_62] = true;
    document.write($intern_56 + base + $intern_63);
  }
  if (!__gwt_scriptsLoaded[$intern_64]) {
    __gwt_scriptsLoaded[$intern_64] = true;
    document.write($intern_56 + base + $intern_65);
  }
  if (!__gwt_scriptsLoaded[$intern_66]) {
    __gwt_scriptsLoaded[$intern_66] = true;
    document.write($intern_56 + base + $intern_67);
  }
  if (!__gwt_scriptsLoaded[$intern_68]) {
    __gwt_scriptsLoaded[$intern_68] = true;
    document.write($intern_56 + base + $intern_69);
  }
  if (!__gwt_scriptsLoaded[$intern_70]) {
    __gwt_scriptsLoaded[$intern_70] = true;
    document.write($intern_56 + base + $intern_71);
  }
  if (!__gwt_scriptsLoaded[$intern_72]) {
    __gwt_scriptsLoaded[$intern_72] = true;
    document.write($intern_56 + base + $intern_73);
  }
  $doc.write($intern_74);
}

login();
