import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class EventList extends Component {

  constructor(props) {
    super(props);
    this.state = {events: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/events')
      .then(response => response.json())
      .then(data => this.setState({events: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/api/event/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedEvents = [...this.state.events].filter(i => i.id !== id);
      this.setState({events: updatedEvents});
    });
  }

  render() {
    const {events, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const eventList = events.map(event => {
      return <tr key={event.id}>
      	<td style={{whiteSpace: 'nowrap'}}>{event.date}</td>
        <td style={{whiteSpace: 'nowrap'}}>{event.title}</td>
        <td style={{whiteSpace: 'nowrap'}}>{event.description}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/events/" + event.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(event.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/events/new">Add Event</Button>
          </div>
          <h3>My JUG Tour</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Date</th>
              <th width="20%">Description</th>
              <th width="20%">Title</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {eventList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default EventList;