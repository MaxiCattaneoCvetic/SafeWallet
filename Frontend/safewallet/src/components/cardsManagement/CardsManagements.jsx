import ActiveCards from "../activeCards/ActiveCards";

function CardsManagements(props) {
  return (
    <>
      <div>
        <ActiveCards setIsModal2={props.setIsModal2} />
      </div>
    </>
  );
}
export default CardsManagements