import React, { useEffect, useState } from 'react'
import { Button, Card, CardBody, CardSubtitle, CardText, CardTitle, Container, NavLink, Pagination, PaginationItem, PaginationLink, Table } from 'reactstrap'
import { loadAllEmp } from '../services/user-service'
import {toast} from 'react-toastify';
import { Link, NavLink as ReactLink, useNavigate} from 'react-router-dom';

import { Base } from './Base';


 const FunctionalityCard = (props) => {
    const navigate=useNavigate();
    const func=()=>{
        navigate(props.nav)
    }
  
  return (

    
    <div className="container">
    <Card
  style={{
    width: '18rem',
    marginTop: '25px'
  }}
>
    {console.log("imahfdh")}{
    console.log(props.img)
    }
  <img
    alt="Sample"
    src={props.img}
  />
  <CardBody>
    <CardTitle tag="h5">
      {props.title}
    </CardTitle>
    {/* <CardSubtitle
      className="mb-2 text-muted"
      tag="h6"
    >
      Card subtitle
    </CardSubtitle> */}
    <CardText>
      {props.msg}
    </CardText>
    <Button onClick={func}>
      {props.btn}
    </Button>
  </CardBody>
</Card>

    </div>
    

  )
}
export default FunctionalityCard;
