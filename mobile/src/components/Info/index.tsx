import { Container, InfoText } from "./styles";

interface InfoProps {
    type: string;
    content: string;
    icon: string;
}

export function Info({ type, content, icon }:InfoProps){
    return <Container>
        <InfoText>
            { icon }
        </InfoText>
        <InfoText>
            { content }
        </InfoText>
    </Container>
}