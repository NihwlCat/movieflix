import "./style.css"
import { ReactComponent as ArrowIcon } from 'core/images/arrow.svg'

type Props = {
    totalPages: number;
    activePage: number;
    onChange: (item: number) => void
}

const generateItems = (amount: number) => {
    return Array.from(Array(amount).keys());
}

const Pagination = ({totalPages, activePage, onChange}:Props) => {
    const items = generateItems(totalPages)

    const previousClass = totalPages > 0 && activePage > 0 ? 'active-page': 'inactive-page';
    const nextClass = (activePage + 1) < totalPages ? 'active-page': 'inactive-page';


    return (<div className="pagination-container">
        <ArrowIcon className={`previous ${previousClass}`}
        onClick={() => onChange(activePage - 1)}
        />
        {items.map(item => (<div key={item} 
        className={`pagination-content ${activePage === item ? 'active' : ''}`} 
        onClick={() => onChange(item)}>
            {item + 1}
        </div>))}

        <ArrowIcon className={nextClass}
        onClick={() => onChange(activePage + 1)}
        />
    </div>)

}

export default Pagination;