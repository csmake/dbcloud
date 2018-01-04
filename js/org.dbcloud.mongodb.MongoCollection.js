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
 * The org.dbcloud.mongodb.MongoCollection javascript interface.
 *
 * <p>Note: Generate by csmake for javascript .</p>
 *
 * {@link org.dbcloud.mongodb.MongoCollection}
 * @author www.csmake.com
 */
var org;
(function(org){
    var dbcloud;
    (function(dbcloud){
        var mongodb;
        (function(mongodb){
            var MongoCollection=
            (function(MongoCollection){
                var _init = false; 
                var _delayList = []; 
                var _delayTimer = null;
                var _guid = 'org.dbcloud.mongodb.MongoCollection';
                var _socket = null;
                var _url =  'org.dbcloud.mongodb.MongoCollection';
                function log(text){  if(typeof console !== 'undefined' && typeof console.log === 'function'){ console.log(text);}}
                function argsError(m,args){m='org.dbcloud.mongodb.MongoCollection.'+m+' arguments is invalid:'+JSON.stringify(args); if (isasync(args) && typeof args[0].fail === 'function'){args[0].fail({message:m});}else{throw new SyntaxError(m); } }
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
                var base =MongoCollection.prototype;
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
                /* public methods*/
                base.drop = function (){
                    var fn = 'drop';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.deleteMany = function (filter){
                    var fn = 'deleteMany';
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
                base.wait = function (){
                    var fn = 'wait';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],1)&& isType(arguments[1+asyn],1)){fn += '2';}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],1)){fn += '3';}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.notifyAll = function (){
                    var fn = 'notifyAll';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.findOneAndReplace = function (filter, replacement){
                    var fn = 'findOneAndReplace';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){}
                    else if(arguments.length === (3+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)&& isType(arguments[2+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.updateOne = function (filter, update){
                    var fn = 'updateOne';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){}
                    else if(arguments.length === (3+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)&& isType(arguments[2+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.findOneAndUpdate = function (filter, update, options){
                    var fn = 'findOneAndUpdate';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (3+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)&& isType(arguments[2+asyn],0)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){fn += '2';}
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
                base.dropIndex = function (keys){
                    var fn = 'dropIndex';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],2)){fn += '2';}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.updateMany = function (filter, update, options){
                    var fn = 'updateMany';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (3+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)&& isType(arguments[2+asyn],0)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.replaceOne = function (filter, replacement, options){
                    var fn = 'replaceOne';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (3+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)&& isType(arguments[2+asyn],0)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.find = function (){
                    var fn = 'find';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (0+asyn) ){}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){fn += '2';}
                    else if(arguments.length === (3+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],1)&& isType(arguments[2+asyn],1)){fn += '3';}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){fn += '4';}
                    else if(arguments.length === (4+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)&& isType(arguments[2+asyn],1)&& isType(arguments[3+asyn],1)){fn += '5';}
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
                base.dropIndexes = function (){
                    var fn = 'dropIndexes';
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
                base.createIndexes = function (indexes){
                    var fn = 'createIndexes';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],3)){}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.renameCollection = function (newCollectionNamespace){
                    var fn = 'renameCollection';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    base.scall(fn, arguments); 
                };
                base.deleteOne = function (filter){
                    var fn = 'deleteOne';
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
                base.count = function (filter, options){
                    var fn = 'count';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){}
                    else if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){fn += '2';}
                    else if(arguments.length === (0+asyn) ){fn += '3';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.insertMany = function (documents){
                    var fn = 'insertMany';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],3)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],3)&& isType(arguments[1+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.createIndex = function (keys){
                    var fn = 'createIndex';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.listIndexes = function (){
                    var fn = 'listIndexes';
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
                base.findOneAndDelete = function (filter){
                    var fn = 'findOneAndDelete';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){fn += '2';}
                    else{argsError(fn,arguments);}
                    if(asyn){
                        base.scall(fn,arguments); 
                    }else{
                        var ret = base.scall(fn, arguments);
                        return ret;
                    }
                };
                base.getCodecRegistry = function (){
                    var fn = 'getCodecRegistry';
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
                base.getDocumentClass = function (){
                    var fn = 'getDocumentClass';
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
                base.getNamespace = function (){
                    var fn = 'getNamespace';
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
                base.insertOne = function (document){
                    var fn = 'insertOne';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (1+asyn) && isType(arguments[0+asyn],0)){}
                    else if(arguments.length === (2+asyn) && isType(arguments[0+asyn],0)&& isType(arguments[1+asyn],0)){fn += '2';}
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
                function MongoCollection(){
                    var fn = 'constructor';
                    var asyn=isasync(arguments)?1:0;
                    if(arguments.length === (5+asyn)&& isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],2)&& isType(arguments[2+asyn],2)&& isType(arguments[3+asyn],2)&& isType(arguments[4+asyn],2)){}
                    else if(arguments.length === (0+asyn)){fn += '2';}
                    else if(arguments.length === (2+asyn)&& isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],2)){fn += '3';}
                    else if(arguments.length === (3+asyn)&& isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],2)&& isType(arguments[2+asyn],2)){fn += '4';}
                    else if(arguments.length === (4+asyn)&& isType(arguments[0+asyn],2)&& isType(arguments[1+asyn],2)&& isType(arguments[2+asyn],2)&& isType(arguments[3+asyn],2)){fn += '5';}
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
                            that.guid(MongoCollection.prototype.scall(fn,args));
                            if(!asyn){
                                _init=true;
                            }
                        }catch(e){
                            log(e.message);
                        }
                    return this;
                }
            var _types=['[object Object]','[object Number]','[object String]','[object Array]'];
            return MongoCollection;
        })(MongoCollection = mongodb.MongoCollection||(mongodb.MongoCollection = {}));
        mongodb.MongoCollection = MongoCollection;
        return mongodb;
    })(mongodb = dbcloud.mongodb||(dbcloud.mongodb = {}));
    dbcloud.mongodb = mongodb;
    return dbcloud;
})(dbcloud = org.dbcloud||(org.dbcloud = {}));
org.dbcloud = dbcloud;
})(org || (org = {}));
