import React, {useEffect} from 'react';
import TableHeaderUtil from '../../shared/utils/TableHeaderUtil' ;
interface TableHeaderProps {
    type: string;
}
function TableHeader({ type }: TableHeaderProps) {

    const tableHeaderUtil = new TableHeaderUtil(type);
    const tableHeader = tableHeaderUtil.getHeaderTable();

    return (
        <div className="main-box">
            {tableHeader.map((table) => (
                <div key={table.title} className={table.class.join(' ')}>
                    <div className="center">{table.title}</div>
                </div>
            ))}
        </div>
    );
}

export default TableHeader;



