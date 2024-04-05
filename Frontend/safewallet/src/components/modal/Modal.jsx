/* eslint-disable react/prop-types */
import { BsXLg } from 'react-icons/bs';
import classes from './Modal.module.css';

const Modal = ({
  onClick,
  title = '',
  children = '',
  buttonText = 'Cerrar',
}) => {
  return (
    <>
      <div className={"blur-background"} />
      <div className={classes.backdrop}>
        <div className={classes.modal}>
          <div className={classes.header}>
            <h2>{title}</h2>
          </div>
          <div className={classes.content}>{children}
          </div>
          <div className={classes.actions}>
            <button className={classes.btn} onClick={onClick}>
              Cerrar
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Modal;
