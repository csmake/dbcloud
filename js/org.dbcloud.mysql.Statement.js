/**
 * Copyright (c) 2012-2018 CSMAKE, Inc. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Generate by csmake for java Thu Jan 04 16:57:10 CST 2018
 * www.csmake.com
 */

/**
 * The org.dbcloud.mysql.Statement javascript interface.
 *
 * <p>Note: Generate by csmake for javascript .</p>
 *
 * {@link org.dbcloud.mysql.Statement}
 * @author www.csmake.com
 */
var org;
(function(org){
    var dbcloud;
    (function(dbcloud){
        var mysql;
        (function(mysql){
            var Statement=
            (function(Statement){
                var _init = false; 
                var _delayList = []; 
                var _delayTimer = null;
                var _guid = 'org.dbcloud.mysql.Statement';
                var _socket = null;
                var _url =  'org.dbcloud.mysql.Statement';
                function log(text){  if(typeof console !== 'undefined' && typeof console.log === 'function'){ console.log(text);}}
                function argsError(m,args){m='org.dbcloud.mysql.Statement.'+m+' arguments is invalid:'+JSON.stringify(args); if (isasync(args) && typeof args[0].fail === 'function'){args[0].fail({message:m});}else{throw new SyntaxError(m); } }
                function isasync(args){  return (args.length >= 1 && args[0] && (typeof args[0].done === 'function'||typeof args[0].fail === 'function'||typeof args[0].always === 'function'));}
                function stringify(start, objs){
                    var args = '{';
                    for(var i=start; i < objs.length;i++){
                        if(i!==start)args+=',';
                        args+='\"'+(i-start)+'\":'+JSON.stringify(objs[i]);
                    }
                    args+='}';
                    return args;
                };
                function _delayCall(){
                    if (_init){
                        if (_delayTimer != null){
                            clearTimeout(_delayTimer);
                            _delayTimer = null;
                        }
                        while (_delayList.length > 0){
                            var f = _delayList.shift();
                            base.scall(f.symbol, f.args);
                        }
                    }
                    else if (_delayTimer == null){
                        _delayTimer = setInterval(function (){
                            _delayCall();
                        }
                        , 500);
                    }
                };
                function isType(o, i) {
                    return Object.prototype.toString.call(o) === _types[i];
                };
                function dumy(){}
                function defineProperty(_this, name, options) {
                    _this[name] = function (v) {
                        var async = isasync(arguments);
                        return  async ? (arguments.length > 1 ? (options.set(arguments[0],arguments[1]), arguments[1]) : options.get(arguments[0])):(arguments.length > 0 ? (options.set(arguments[0]), arguments[0]) : options.get());
                    };
                };
                var base =Statement.prototype;
                base.scall = function (symbol, args) {
                    var async = isasync(args);
                    if (async && !_init && symbol != 'constructor') {
                      _delayList.push({ symbol:symbol, args:args });
                      _delayCall();
                      return;
                    }
                    var done = async ? args[0].done||dumy : dumy;
                    var fail = async ? args[0].fail|| log : function(o){throw o;};
                    var always = async ? args[0].always||dumy:dumy;
                    var time=(new Date()).getTime();
                    var conv =async ? args[0].conv || function (o) { return o; }:function (o) { return o; };
                    var message = "{'time':"+time+",'guid':'" + _guid + "','symbol':'" + symbol + "','args':" + stringify(async?1:0, args) + "}";
                    log('scall:' + symbol + '\nsend:' + message + '\n');
                    var obj;
                    $.ajax({
                        method: "POST",
                        dataType: "text",
                        url: _url,
                        cache: false,
                        async: async,
                        data: message
                    }).done(function (ret) {
                        log(symbol + ' '+time+ ' recv:' + ret + '\n');
                        obj = JSON.parse(ret);
                        try {
                    if (obj.error > 200) {
                		log(symbol + ' invoke error:' + obj.error + ' message:' + obj.message);
                        fail({'name': obj.error ,'message':obj.message});
                        return;
                    }
                    obj = obj.value;
                    done(conv((obj && obj._v) ? obj._v : obj));
                        } catch (e) {
                    fail(e);
                        }
                    }).fail(function (jqXHR, textStatus) {
                        if (textStatus === "parsererror") {
                    try {
                        var obj = JSON.parse(jqXHR.responseText);
                        if (obj.error > 200) {
                		log(symbol + ' invoke error:' + obj.error + ' message:' + obj.message);
                        fail({'name': obj.error ,'message':obj.message});
                            return;
                        }
                        obj = obj.value;
                        done(conv((obj && obj._v) ? obj._v : obj));
                    } catch (e) {
                        fail(e);
                    }
                        } else {
                    fail({'name':500,'message': JSON.stringify(jqXHR)});
                        }
                    }).always(always);
                    return obj;
                };
                defineProperty(base, "guid", {
                    get: function () {
                        return _guid;
                    },
                    set: function (val) {
                        _guid = val;
                    }
                });
                /*public fields*/
                defineProperty(base,'configs',{
                    get: function(){
                        return base.scall('get_configs', arguments);
                    },
                    set: function(v){
                        base.scall('set_configs', arguments);
                    }
                });
                defineProperty(base,'id',{
                    get: function(){
                        return base.scall('get_id', arguments);
                    },
                    set: function(v){
                        base.scall('set_id', arguments);
                    }
                });
                defineProperty(base,'CLOSE_CURRENT_RESULT',{
                    get: function(){
                        return base.scall('get_CLOSE_CURRENT_RESULT', arguments);
                    },
                    set: function(v){
                        base.scall('set_CLOSE_CURRENT_RESULT', arguments);
                    }
                });
                defineProperty(base,'KEEP_CURRENT_RESULT',{
                    get: function(){
                        return base.scall('get_KEEP_CURRENT_RESULT', arguments);
                    },
                    set: function(v){
                        base.scall('set_KEEP_CURRENT_RESULT', arguments);
                    }
                });
                defineProperty(base,'CLOSE_ALL_RESULTS',{
                    get: function(){
                        return base.scall('get_CLOSE_ALL_RESULTS', arguments);
                    },
                    set: function(v){
                        base.scall('set_CLOSE_ALL_RESULTS', arguments);
                    }
                });
                defineProperty(base,'SUCCESS_NO_INFO',{
                    get: function(){
                        return base.scall('get_SUCCESS_NO_INFO', arguments);
                    },
                    set: function(v){
                        base.scall('set_SUCCESS_NO_INFO', arguments);
                    }
                });
                defineProperty(base,'EXECUTE_FAILED',{
                    get: function(){
                        return base.scall('get_EXECUTE_FAILED', arguments);
                    },
                    set: function(v){
                        base.scall('set_EXECUTE_FAILED', arguments);
                    }
                });
                defineProperty(base,'RETURN_GENERATED_KEYS',{
                    get: function(){
                        return base.scall('get_RETURN_GENERATED_KEYS', arguments);
                    },
                    set: function(v){
                        base.scall('set_RETURN_GENERATED_KEYS', arguments);
                    }
                });
                defineProperty(base,'NO_GENERATED_KEYS',{
                    get: function(){
                        return base.scall('get_NO_GENERATED_KEYS', arguments);
                    },
                    set: function(v){
                        base.scall('set_NO_GENERATED_KEYS', arguments);
                    }
                });
                /* public methods*/
                base.cancel = function (){
                    var fn = 'cancel';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.getClass = function (){
                    var fn = 'getClass';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.executeLargeUpdate = function (sql, columnNames){
                    var fn = 'executeLargeUpdate';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],3)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],3)){fn += '2';}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],2)){fn += '3';}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],1)){fn += '4';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.wait = function (){
                    var fn = 'wait';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],1)&& isType(arguments[1+asyn],1)){fn += '2';}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){fn += '3';}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.getResultSetHoldability = function (){
                    var fn = 'getResultSetHoldability';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getLargeUpdateCount = function (){
                    var fn = 'getLargeUpdateCount';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getQueryTimeout = function (){
                    var fn = 'getQueryTimeout';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.notifyAll = function (){
                    var fn = 'notifyAll';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.getMaxFieldSize = function (){
                    var fn = 'getMaxFieldSize';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getUpdateCount = function (){
                    var fn = 'getUpdateCount';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.setPoolable = function (poolable){
                    var fn = 'setPoolable';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],4)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.getFetchSize = function (){
                    var fn = 'getFetchSize';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.notify = function (){
                    var fn = 'notify';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.setQueryTimeout = function (seconds){
                    var fn = 'setQueryTimeout';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.setFetchDirection = function (direction){
                    var fn = 'setFetchDirection';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.getConnection = function (){
                    var fn = 'getConnection';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.setLargeMaxRows = function (max){
                    var fn = 'setLargeMaxRows';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.setMaxRows = function (max){
                    var fn = 'setMaxRows';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.executeProcedure = function (procedureName, parameters, options){
                    var fn = 'executeProcedure';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (3+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],0)&& isType(arguments[2+asyn],0)){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.hashCode = function (){
                    var fn = 'hashCode';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.setMaxFieldSize = function (max){
                    var fn = 'setMaxFieldSize';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.isPoolable = function (){
                    var fn = 'isPoolable';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.setCursorName = function (name){
                    var fn = 'setCursorName';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],2)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.clearBatch = function (){
                    var fn = 'clearBatch';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.close = function (){
                    var fn = 'close';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.getFetchDirection = function (){
                    var fn = 'getFetchDirection';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.closeOnCompletion = function (){
                    var fn = 'closeOnCompletion';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.getResultSetType = function (){
                    var fn = 'getResultSetType';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.executeBatch = function (){
                    var fn = 'executeBatch';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getGeneratedKeys = function (){
                    var fn = 'getGeneratedKeys';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.setFetchSize = function (rows){
                    var fn = 'setFetchSize';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.getMoreResults = function (current){
                    var fn = 'getMoreResults';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){}
                    else if(arguments.length === (0+asyn) ){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.clearWarnings = function (){
                    var fn = 'clearWarnings';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.addBatch = function (sql){
                    var fn = 'addBatch';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],2)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.execute = function (sqls){
                    var fn = 'execute';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],3)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],3)){fn += '2';}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],3)){fn += '3';}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],2)){fn += '4';}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],1)){fn += '5';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.isCloseOnCompletion = function (){
                    var fn = 'isCloseOnCompletion';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.executeQuery = function (sql, options){
                    var fn = 'executeQuery';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],0)){}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],2)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getResultSetConcurrency = function (){
                    var fn = 'getResultSetConcurrency';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.isClosed = function (){
                    var fn = 'isClosed';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.executeLargeBatch = function (){
                    var fn = 'executeLargeBatch';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getResultSet = function (){
                    var fn = 'getResultSet';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.equals = function (arg0){
                    var fn = 'equals';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.toString = function (){
                    var fn = 'toString';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getMaxRows = function (){
                    var fn = 'getMaxRows';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.setEscapeProcessing = function (enable){
                    var fn = 'setEscapeProcessing';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],4)){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.executeUpdate = function (sql){
                    var fn = 'executeUpdate';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],2)){}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],3)){fn += '2';}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],3)){fn += '3';}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],3)){fn += '4';}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],1)){fn += '5';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getLargeMaxRows = function (){
                    var fn = 'getLargeMaxRows';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                function Statement(){
                    var fn = 'constructor';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn)){}
                    else if(arguments.length === (1+asyn)&& isType(arguments[0+asyn],2)){fn += '2';}
                    else if(arguments.length === (3+asyn)&& isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],2)&& isType(arguments[2+asyn],2)){fn += '3';}
                    else{argsError(fn, arguments);}
                        try{
                            var args=[];
                            var that=this;
                            if(asyn){
                                var cb=arguments[0];
                                if(typeof cb.done === 'function'){
                                    var old=cb.done;
                                    cb.done=function(id){that.guid(id);_init=true;old(that);}
                                }
                                else{
                                    cb.done=function(id){that.guid(id);_init=true;}
                                }
                                args.push(cb);
                            }
                            for(var i=asyn; i< arguments.length; i++){
                                args.push(arguments[i]);
                            }
                            that.guid(Statement.prototype.scall(fn,args));
                            if(!asyn){
                                _init=true;
                            }
                        }catch(e){
                            log(e.message);
                        }
                    return this;
                }
            var _types=['[object Object]','[object Number]','[object String]','[object Array]','[object Boolean]'];
            return Statement;
        })(Statement = mysql.Statement||(mysql.Statement = {}));
        mysql.Statement = Statement;
        return mysql;
    })(mysql = dbcloud.mysql||(dbcloud.mysql = {}));
    dbcloud.mysql = mysql;
    return dbcloud;
})(dbcloud = org.dbcloud||(org.dbcloud = {}));
org.dbcloud = dbcloud;
})(org || (org = {}));
