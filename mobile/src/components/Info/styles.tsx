import styled from "styled-components/native";

export const Container = styled.View`
    display: flex;
    flex-direction: row;
    align-items: center;
    border-radius: 16px;
    background-color: ${ ({theme}) => theme.colors.sageSoft};
    padding: 14px;
    gap: 8px;
`

export const InfoText = styled.Text`
    flex-shrink: 1;
    font-size: 14px;
    color: ${ props => props.theme.colors.ink};
    font-family: ${ props => props.theme.fonts.body.regular};
`