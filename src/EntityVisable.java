
public class EntityVisable 
{
	float m_X,m_Y,m_Scale;
	
	void SetPos(float X, float Y)
	{
		m_X = X;
		m_Y = Y;
	}
	
	void SetPos(float X, float Y, float Scale)
	{
		m_X = X;
		m_Y = Y;
		m_Scale = Scale;
	}
	
	void SetScale(float Scale)
	{
		m_Scale = Scale;
	}
	
}
