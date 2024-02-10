import React from 'react';
import GridComponent, {StyledContent, StyledSetion} from "@/api/common/GridComponent";
import {useQuery} from "@tanstack/react-query";
import {RankApi} from "@/api/RankApi";

function MainSection(props) {

    const {}=useQuery(
        ['clickRank'],
        ()=>RankApi.readClickRank(), {
            onSuccess: data =>{
                console.log(data);
            }
        }
    )
    return (
        <StyledContent isFull={true}>
            <StyledSetion isFull={true}>
                <GridComponent title={"💎베스트 상품"}/>
            </StyledSetion>
        </StyledContent>

    );
}

export default MainSection;