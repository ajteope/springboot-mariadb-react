import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import GroupList from './GroupList';
import GroupEdit from './GroupEdit';
import EventList from './EventList';
import EventEdit from './EventEdit';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/groups' exact={true} component={GroupList}/>
          <Route path='/groups/:id' component={GroupEdit}/>
          <Route path='/events' exact={true} component={EventList}/>
          <Route path='/events/:id' component={EventEdit}/>
        </Switch>
      </Router>
    )
  }
}

export default App;