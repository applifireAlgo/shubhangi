Ext.define('Testapp3.testapp3.web.com.view.salesboundedcontext.sales.MaterialMain', {
     "xtype": "material",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "MaterialMainController",
     "restURL": "/Material",
     "defaults": {
          "split": true
     },
     "requires": ["Testapp3.testapp3.shared.com.model.salesboundedcontext.sales.MaterialModel", "Testapp3.testapp3.web.com.controller.salesboundedcontext.sales.MaterialMainController", "Testapp3.testapp3.shared.com.model.salesboundedcontext.sales.BrandModel", "Testapp3.testapp3.shared.com.viewmodel.salesboundedcontext.sales.MaterialViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Material",
               "name": "MaterialTreeContainer",
               "itemId": "MaterialTreeContainer",
               "restURL": "/Material",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "MaterialTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Material",
                    "title": "Material",
                    "name": "Material",
                    "itemId": "MaterialForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "materialcode",
                         "itemId": "materialcode",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Material Code",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Material Code<font color='red'> *<\/font>",
                         "fieldId": "921C9A47-DB35-41E8-B729-1FE78C6F2023",
                         "hidden": true,
                         "value": "",
                         "bindable": "materialcode"
                    }, {
                         "name": "materialdesc",
                         "itemId": "materialdesc",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Material",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Material<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "A5ECEEEA-0B5F-45C8-85E3-02F49A68982E",
                         "minLength": "0",
                         "maxLength": "64",
                         "bindable": "materialdesc",
                         "columnWidth": 0.5
                    }, {
                         "name": "brandcode",
                         "itemId": "brandcode",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Brand",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Testapp3.testapp3.shared.com.model.salesboundedcontext.sales.BrandModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Brand<font color='red'> *<\/font>",
                         "fieldId": "7FCC55BD-337A-4FA5-B2F9-383E481BDD7D",
                         "restURL": "Brand",
                         "bindable": "brandcode",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "85A98ACC-2BAD-4296-88DE-6249C3437750",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 390,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 390,
                              "customId": 16
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 390,
                              "customId": 17,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 390,
                              "customId": 18,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Material",
                    "title": "Details Grid",
                    "name": "MaterialGrid",
                    "itemId": "MaterialGrid",
                    "restURL": "/Material",
                    "store": [],
                    "columns": [{
                         "header": "Material Code",
                         "dataIndex": "materialcode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Material",
                         "dataIndex": "materialdesc",
                         "flex": 1
                    }, {
                         "header": "Brand",
                         "dataIndex": "brandcode",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Material",
               "title": "Material",
               "name": "Material",
               "itemId": "MaterialForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "materialcode",
                    "itemId": "materialcode",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Material Code",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Material Code<font color='red'> *<\/font>",
                    "fieldId": "921C9A47-DB35-41E8-B729-1FE78C6F2023",
                    "hidden": true,
                    "value": "",
                    "bindable": "materialcode"
               }, {
                    "name": "materialdesc",
                    "itemId": "materialdesc",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Material",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Material<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "A5ECEEEA-0B5F-45C8-85E3-02F49A68982E",
                    "minLength": "0",
                    "maxLength": "64",
                    "bindable": "materialdesc",
                    "columnWidth": 0.5
               }, {
                    "name": "brandcode",
                    "itemId": "brandcode",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Brand",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Testapp3.testapp3.shared.com.model.salesboundedcontext.sales.BrandModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Brand<font color='red'> *<\/font>",
                    "fieldId": "7FCC55BD-337A-4FA5-B2F9-383E481BDD7D",
                    "restURL": "Brand",
                    "bindable": "brandcode",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "85A98ACC-2BAD-4296-88DE-6249C3437750",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 390,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 390,
                         "customId": 16
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 390,
                         "customId": 17,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 390,
                         "customId": 18,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});