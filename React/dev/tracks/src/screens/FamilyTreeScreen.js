import React, { Component } from 'react'
import { View, StyleSheet, ScrollView, } from 'react-native'
import { SafeAreaView } from 'react-navigation'
import { Text } from 'react-native-elements'
import { AsyncStorage } from 'react-native'
//import NetworkGraph from 'react-native-network-graph';
//import { VictoryBar, VictoryChart, VictoryTheme, Line } from "victory-native";
//import Graph from 'react-graph-network';
// import {Tree} from 'react-native-pathjs-charts';
import {Context as AuthContext} from '../context/AuthContext'
import ReactNativeZoomableView from '@dudigital/react-native-zoomable-view/src/ReactNativeZoomableView';
import AwesomeHierarchyGraph from 'react-native-d3-tree-graph'

const { state, getUserRelations} = useContext(AuthContext);
class FamilyTreeScreen extends Component {
  
  constructor(props) {
    super(props);
    this.state = {
      selectedCircleIndex:0  //this can also be stored in redux store.
    };
  }
 
  onCircleClick(index) {  //or an action can be dispatched as well.
    this.setState({
      selectedCircleIndex:index
    })
  };
  
      //<Graph data={data}/>
    render() {
      console.log(root[0]);
      let user =  AsyncStorage.getItem('user_id');
      let data= 
      //data["name"] =user;
      console.log(data.type)
      let connections = {
        "1":[2,4], //node at index 1 is connected to nodes at index 2 and 4 respectively.
        "2":[6,7] //node at index 2 is connected to nodes at index 6 and 7 respectively.
      };
      let circleTitles = ['C1','C2', 'C3', 'C4', 'C5','C6', 'C7', 'C8', 'C9'];
      

    return (
        
          <View style={styles.container}>
            {/* <Text h3>Family Tree</Text> */}
        
          <AwesomeHierarchyGraph
         root = {root}
         siblings = {siblings}
        />
          {/* <Tree data={data} options={options}  /> */}
        

          </View>
        
        
    );
  }

  
};

var root = {
  name: "",
  id: 1,
  hidden: true,
  children: [ {
          name: "Q",
          id: 16,
          no_parent: true,
          imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
          nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
          nodeTextStyle: { fontSize: 12 }
      },
      {
      name: "",
      id: 2,
      no_parent: true,
      hidden: true,
      children: [{
          name: "J",
          id: 12,
          imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
          nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
          nodeTextStyle: { fontSize: 12 }
      }, {
          name: "L",
          id: 13,
          no_parent: true,
          imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
          nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
          nodeTextStyle: { fontSize: 12 }
      }, {
          name: "C",
          id: 3,
          imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
          nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
          nodeTextStyle: { fontSize: 12 }
      }, {
          name: "A",
          id: 4,
          hidden: true,
          no_parent: true,
          children: [{
              name: "D",
              id: 5,
              imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
              nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
              nodeTextStyle: { fontSize: 12 }
          }, {
              name: "",
              id: 14,
              hidden: true,
              no_parent: true,
              children: [{
                  name: "P",
                  id: 15,
                  imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
                  nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
                  nodeTextStyle: { fontSize: 12 },
                  children: [{
                    name: "a",
                    id: 111,
                    imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
                    nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
                    nodeTextStyle: { fontSize: 12 }
                }]
              }]
          }, {
              name: "E",
              id: 6,
              imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
              nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
              nodeTextStyle: { fontSize: 12 }
          }]
      }, {
          name: "K",
          id: 11,
          imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
          nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
          nodeTextStyle: { fontSize: 12 }
      }, {
          name: "G",
          id: 7,
          imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
          nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
          nodeTextStyle: { fontSize: 12 },
          children: [{
              name: "H",
              id: 8,
              imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
              nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
              nodeTextStyle: { fontSize: 12 },
              children: [{
                name: "H1",
                id: 81,
                imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
                nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
                nodeTextStyle: { fontSize: 12 }
            }, {
                name: "I1",
                id: 91,
                imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
                nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
                nodeTextStyle: { fontSize: 12 }
            }]
          }, {
              name: "I",
              id: 9,
              imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
              nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
              nodeTextStyle: { fontSize: 12 }
          }]
      }]
  }, {
      name: "aas",
      id: 10,
      no_parent: true,
      imageUrl: { href: { uri: "https://facebook.github.io/react-native/docs/assets/favicon.png"}},
      nodeImageStyle: { imageHeight: 60 , imageWidth: 60, opacity: 1 },
      nodeTextStyle: { fontSize: 12 },
      children: [
        
      ]
  },

]
}

var siblings = [{
  source: {
      id: 3,
      name: "C"
  },
  target: {
      id: 11,
      name: "K"
  }
}, {
  source: {
      id: 12,
      name: "L"
  },
  target: {
      id: 5,
      name: "D"
  }
}, {
  source: {
      id: 5,
      name: "D"
  },
  target: {
      id: 6,
      name: "E"
  }
}, {
  source: {
      id: 16,
      name: "Q"
  },
  target: {
      id: 10,
      name: "M"
  }
}];
    let user =  AsyncStorage.getItem('user_id');
    let data = {
      "name": "Me",
      "children": [{
        "name": "Santa Catarina",
        "children": [{
          "name": "Tromp",
          "children": [{
            "name": "Tromp"
          }, {
            "name": "Thompson"
          }, {
            "name": "Ryan",
            "children": [{
              "name": "Tromp",
              "children": [{
                "name": "Tromp"
              }, {
                "name": "Thompson"
              }, {
                "name": "Ryan",
                "id":"12"
              }],
            }, {
              "name": "Thompson"
            }, {
              "name": "Ryan",
              "id":"12"
            }],
            "id":"12"
          }]
        }, {
          "name": "Thompson"
        }, {
          "name": "Ryan",
          "id":"12",
          "children": [{
            "name": "Tromp",
            "children": [{
              "name": "Tromp"
            }, {
              "name": "Thompson"
            }, {
              "name": "Ryan",
              "id":"12"
            }],
          }, {
            "name": "Thompson"
          }, {
            "name": "Ryan",
            "id":"12"
          }],
        }]
      }, {
        "name": "Acre",
        "children": [{
          "name": "Dicki"
        }, {
          "name": "Armstrong"
        }, {
          "name": "Nitzsche"
        }]
      }],
    };

    let options = {
      margin: {
        top: 20,
        left: 50,
        right: 80,
        bottom: 20
      },
      width: 200,
      height: 200,
      fill: "#2980B9",
      stroke: "#3E90F0",
      r: 2,
      animate: {
        type: 'oneByOne',
        duration: 200,
        fillTransition: 3
      },
      label: {
        fontFamily: 'Arial',
        fontSize: 8,
        fontWeight: true,
        fill: '#34495E'
      }
    };

const styles = StyleSheet.create({
    container: {
      flex: 1,
        marginTop: 100,
        marginBottom: 100,
        justifyContent: 'space-evenly',
        overflow: 'scroll',
        alignItems: 'center',
    backgroundColor: 'white'
        
    },
    map: {
      position: 'absolute',
      top: 0,
      left: 0,
      right: 0,
      bottom: 0,
    }
});

export default FamilyTreeScreen;