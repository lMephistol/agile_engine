import React from 'react';
import PropTypes from 'prop-types';
import {makeStyles, withStyles} from '@material-ui/core/styles';
import {Accordion, AccordionSummary, AccordionDetails} from '@material-ui/core';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

const useStyles = makeStyles({
  root: {
    width: '100%',
  },
  heading: {
    fontSize: 15,
  },
  content: {
    width: '100%'
  }
});

const MyAccordionSummary = withStyles({
  root: {
    background: ({color}) =>
        color === 'red'
            ? 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)'
            : 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)',
  }
})(AccordionSummary);

function TransactionList(props) {
  const {data} = props;
  const classes = useStyles();

  return (
      <div className={classes.root}>
        {data.length !== 0 && data.sort((a,b) =>  b.effectiveDate.localeCompare(a.effectiveDate)).map(item =>
            <Accordion key={item.id}>
              <MyAccordionSummary
                  color={item.type === 'credit' ? 'blue' : 'red'}
                  className={classes.summary}
                  expandIcon={<ExpandMoreIcon/>}>
                <Typography className={classes.heading}>Type : {item.type} Amount: {item.amount}</Typography>
              </MyAccordionSummary>
              <AccordionDetails>
                <Typography
                    className={classes.content} variant='h5'>ID
                  : {item.id}</Typography>
                <Typography
                    className={classes.content} variant='h5'>EffectiveDate
                  : {item.effectiveDate}</Typography>
              </AccordionDetails>
            </Accordion>)}
      </div>
  );
}

TransactionList.propTypes = {
  data: PropTypes.array.isRequired,
};

export default TransactionList;
